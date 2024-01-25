package se.fabricioflores.petmarket.service;

import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import se.fabricioflores.petmarket.dto.AdSubmission;
import se.fabricioflores.petmarket.exception.AdNotFoundException;
import se.fabricioflores.petmarket.model.Ad;
import se.fabricioflores.petmarket.model.User;
import se.fabricioflores.petmarket.projection.AdProjection;
import se.fabricioflores.petmarket.projection.AdWithUserInfoProjection;
import se.fabricioflores.petmarket.repository.AdRepository;

@Service
public class AdService {

  private final AdRepository adRepository;
  private final UserService userService;

  public AdService(AdRepository adRepository, UserService userService) {
    this.adRepository = adRepository;
    this.userService = userService;
  }

  public Ad createAd(AdSubmission adSubmission, Long ownerId) {
    User user = userService.loadUserById(ownerId);
    return adRepository.save(AdSubmission.mappToAd(adSubmission, user));
  }

  /**
   * @throws AdNotFoundException
   */
  public AdProjection getAd(@Valid @PositiveOrZero Long id) {
    return adRepository.findOneById(id).orElseThrow(() -> new AdNotFoundException());
  }

  /**
   * @throws AdNotFoundException
   */
  public AdWithUserInfoProjection getAdWithUserInfo(@Valid @PositiveOrZero Long id) {
    return adRepository.findOneWithUserById(id).orElseThrow(() -> new AdNotFoundException());
  }

}
