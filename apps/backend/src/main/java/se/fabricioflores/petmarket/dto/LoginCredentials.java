package se.fabricioflores.petmarket.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;

public record LoginCredentials(
  @NotNull @Length(min = 2, max = 40)
  String username,
  @NotNull @Length(min = 8, max = 40)
  String password
) {

}
