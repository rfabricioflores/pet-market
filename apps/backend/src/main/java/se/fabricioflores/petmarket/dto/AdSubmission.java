package se.fabricioflores.petmarket.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AdSubmission(
  @NotNull @NotEmpty
  String title,

  String description,

  @NotNull @PositiveOrZero
  BigDecimal price
) {

}
