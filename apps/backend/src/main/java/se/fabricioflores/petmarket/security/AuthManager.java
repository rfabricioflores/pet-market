package se.fabricioflores.petmarket.security;

import java.util.Collection;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthManager {

  public static boolean isAuthenticated() {
    return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthPrincipal ? true : false;
  }

  /**
   * Provides information about the authenticated user,
   * the {@link Optional optional} will be empty if no user is authenticated.
   */
  public static Optional<AuthPrincipal> getPrincipal() {
    if(!isAuthenticated()) return Optional.empty();
    return Optional.of((AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }

  /**
   * @throws RuntimeException
   */
  public static Collection<? extends GrantedAuthority> getPrincipalAuthorities() {
    if(!isAuthenticated()) throw new RuntimeException("Not authenticated");
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
  }

}
