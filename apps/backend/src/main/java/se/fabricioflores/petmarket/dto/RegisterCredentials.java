package se.fabricioflores.petmarket.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;

public record RegisterCredentials(
  @NotNull @Length(min = 2, max = 40)
  String username,

  @NotNull @Length(min = 8, max = 40)
  String password,

  @NotNull @Length(min = 2, max = 40)
  String firstname,

  @NotNull @Length(min = 2, max = 40)
  String lastname,

  @Length(min = 10, max = 10)
  String phone,

  @Length(min = 5, max = 64)
  String email
) {

}
