package se.fabricioflores.petmarket.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import se.fabricioflores.petmarket.dto.AdSubmission;
import se.fabricioflores.petmarket.exception.AdNotFoundException;
import se.fabricioflores.petmarket.model.Ad;
import se.fabricioflores.petmarket.model.AdPhoto;
import se.fabricioflores.petmarket.model.User;
import se.fabricioflores.petmarket.projection.AdProjection;
import se.fabricioflores.petmarket.projection.AdWithUserInfoProjection;
import se.fabricioflores.petmarket.repository.AdRepository;
import se.fabricioflores.petmarket.security.AuthManager;
import se.fabricioflores.petmarket.security.AuthPrincipal;

@Service
public class AdService {

  private final AdRepository adRepository;
  private final UserService userService;

  public AdService(AdRepository adRepository, UserService userService) {
    this.adRepository = adRepository;
    this.userService = userService;
  }

  public Ad createAd(AdSubmission adSubmission) {
    AuthPrincipal principal = AuthManager.getPrincipal().get();
    User user = userService.loadUserById(principal.getId());

    Ad ad = AdSubmission.mappToAd(adSubmission, user);

    if(adSubmission.photos() != null && !adSubmission.photos().isEmpty()) {
      Set<AdPhoto> photos = new HashSet<>();

      adSubmission.photos().stream().forEach((url) -> {
        AdPhoto adPhoto = new AdPhoto();
        adPhoto.setUrl(url);
        adPhoto.setAd(ad);
        photos.add(adPhoto);
      });

      ad.setPhotos(photos);
    }

    return adRepository.save(ad);
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

  public Page<AdProjection> getAdsWithPagination(int pageNumber, int pageSize) {
    return adRepository.findAllBy(PageRequest.of(pageNumber, pageSize));
  }

  public Page<AdProjection> searchAdsByTitle(String title, int pageNumber, int pageSize) {
    return adRepository.findAllByTitleContaining(title, PageRequest.of(pageNumber, pageSize));
  }

}
