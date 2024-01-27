package se.fabricioflores.petmarket.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import se.fabricioflores.petmarket.model.AdPhoto;

@JsonPropertyOrder({
  "id", "title", "description", "price", "photos", "editedAt", "createdAt"
})
public interface AdProjection {
  Long getId();
  String getTitle();
  String getDescription();
  BigDecimal getPrice();
  Set<AdPhoto> getPhotos();
  LocalDateTime getEditedAt();
  LocalDateTime getCreatedAt();
}
