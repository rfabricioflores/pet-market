package se.fabricioflores.petmarket.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import se.fabricioflores.petmarket.model.AdPhoto;

/**
 * Provides detailed information about an ad and a summary of its owner
 */
@JsonPropertyOrder({
  "id", "title", "description", "price", "photos", "owner", "editedAt", "createdAt"
})
public interface AdWithUserInfoProjection{
  Long getId();
  String getTitle();
  String getDescription();
  BigDecimal getPrice();
  Set<AdPhoto> getPhotos();
  UserSummaryProjection getOwner();
  LocalDateTime getEditedAt();
  LocalDateTime getCreatedAt();
}
