package se.fabricioflores.petmarket.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Provides detailed information about an ad and a summary of its owner
 */
@JsonPropertyOrder({
  "id", "title", "description", "price", "owner", "editedAt", "createdAt"
})
public interface AdWithUserInfoProjection{
  Long getId();
  String getTitle();
  String getDescription();
  BigDecimal getPrice();
  UserSummaryProjection getOwner();
  LocalDateTime getEditedAt();
  LocalDateTime getCreatedAt();
}
