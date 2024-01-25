package se.fabricioflores.petmarket.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AdProjection(
  Long id,
  String title,
  String description,
  BigDecimal price,
  LocalDateTime editedAt,
  LocalDateTime createdAt
) {

}
