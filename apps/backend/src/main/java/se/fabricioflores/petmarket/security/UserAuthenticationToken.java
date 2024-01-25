package se.fabricioflores.petmarket.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserAuthenticationToken extends AbstractAuthenticationToken {
  private static final long serialVersionUID = 1L;

  private AuthPrincipal principal;

  public UserAuthenticationToken(AuthPrincipal principal, List<String> roles) {
    super(toAuthorities(roles));
    this.principal = principal;
    super.setAuthenticated(true);
  }

  private static Collection<? extends GrantedAuthority> toAuthorities(List<String> authorityList){
    return authorityList.stream().map(SimpleGrantedAuthority::new).toList();
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public AuthPrincipal getPrincipal() {
    return principal;
  }

  @Override
  public String getName() {
    return principal.getUsername();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getClass().getSimpleName()).append(" [");
    sb.append("Principal=").append(this.principal.getUsername()).append(", ");
    sb.append("Authenticated=").append(this.isAuthenticated()).append(", ");
    sb.append("Granted Authorities=").append(this.getAuthorities());
    sb.append("]");
    return sb.toString();
  }

}
