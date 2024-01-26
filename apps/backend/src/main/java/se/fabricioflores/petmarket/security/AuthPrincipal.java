package se.fabricioflores.petmarket.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthPrincipal {
  private Long id;
  private String username;
}
