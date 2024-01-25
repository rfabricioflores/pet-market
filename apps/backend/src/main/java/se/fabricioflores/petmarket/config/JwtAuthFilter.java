package se.fabricioflores.petmarket.config;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.fabricioflores.petmarket.security.AuthPrincipal;
import se.fabricioflores.petmarket.security.UserAuthenticationToken;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;

  public JwtAuthFilter(JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String token = request.getHeader("Authorization");

    if(token != null && token.startsWith("Bearer")) authenticate(token);

    filterChain.doFilter(request, response);
  }

  private void authenticate(String token) {
    try {
      Claims claims = jwtProvider.validateToken(token.replace("Bearer ", ""));

      if(SecurityContextHolder.getContext().getAuthentication() == null) {
        AuthPrincipal principal = new AuthPrincipal();
        principal.setUsername(claims.getSubject());
        principal.setId(claims.get("id", Long.class));

        Authentication auth = new UserAuthenticationToken(principal, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }

    } catch(JwtException e) {
      logger.warn("JWT Token Validation Failed");
    }
  }
}
