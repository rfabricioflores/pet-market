package se.fabricioflores.petmarket.dto;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import se.fabricioflores.petmarket.model.Ad;
import se.fabricioflores.petmarket.model.User;

public record AdSubmission(
  @NotNull @NotEmpty
  String title,

  String description,

  @NotNull @PositiveOrZero
  BigDecimal price,

  List<String> photos
) {
  public static Ad mappToAd(AdSubmission submission, User owner) {
    Ad ad = new Ad();
    ad.setTitle(submission.title());
    ad.setDescription(submission.description());
    ad.setPrice(submission.price());
    ad.setOwner(owner);
    return ad;
  }
}
