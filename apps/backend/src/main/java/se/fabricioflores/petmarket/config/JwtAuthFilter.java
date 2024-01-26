package se.fabricioflores.petmarket.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.fabricioflores.petmarket.security.AuthPrincipal;
import se.fabricioflores.petmarket.security.UserAuthenticationToken;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;
  private final ObjectMapper objectMapper;

  public JwtAuthFilter(JwtProvider jwtProvider, ObjectMapper objectMapper) {
    this.jwtProvider = jwtProvider;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String token = extractToken(request);

    if(token != null) {
      if(!authenticate(token)) {
        String json = objectMapper.writeValueAsString(Map.of("error", "You don't have access to this resource"));

        response.setStatus(403);
        response.setContentType("application/json");
        response.getWriter().write(json);

        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
        return header.replace("Bearer ", "");
    }
    return null;
  }

  private boolean authenticate(String token) {
    try {
      Claims claims = jwtProvider.validateToken(token);

      if(SecurityContextHolder.getContext().getAuthentication() == null) {
        AuthPrincipal principal = new AuthPrincipal();
        principal.setUsername(claims.getSubject());
        principal.setId(claims.get("id", Long.class));

        Authentication auth = new UserAuthenticationToken(principal, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }

      return true;

    } catch(RuntimeException e) {
      logger.warn("Authentication failed: Not valid token provided");
      SecurityContextHolder.clearContext();
      return false;
    }
  }

}
