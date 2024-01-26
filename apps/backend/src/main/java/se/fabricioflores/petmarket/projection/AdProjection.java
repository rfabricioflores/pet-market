package se.fabricioflores.petmarket.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AdProjection {
  Long getId();
  String getTitle();
  String getDescription();
  BigDecimal getPrice();
  LocalDateTime getEditedAt();
  LocalDateTime getCreatedAt();
}
