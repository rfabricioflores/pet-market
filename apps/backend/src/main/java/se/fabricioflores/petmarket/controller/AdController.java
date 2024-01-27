package se.fabricioflores.petmarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import se.fabricioflores.petmarket.dto.AdSubmission;
import se.fabricioflores.petmarket.security.AuthManager;
import se.fabricioflores.petmarket.service.AdService;

@RestController
@RequestMapping("/api/ad")
public class AdController {

  private final AdService adService;

  public AdController(AdService adService) {
    this.adService = adService;
  }

  /**
   * Public endpoint, requires authentication for detailed information.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> retrieveAdById(@PathVariable(name = "id") Long id) {
    System.out.println(AuthManager.isAuthenticated());
    if(AuthManager.isAuthenticated()) return ResponseEntity.ok().body(adService.getAdWithUserInfo(id));
    return ResponseEntity.ok().body(adService.getAd(id));
  }

  /**
   * Requires authentication
   */
  @PostMapping()
  public ResponseEntity<Object> createAd(@RequestBody @Valid AdSubmission adSubmission) {
    return ResponseEntity.ok().body(adService.createAd(adSubmission));
  }

  /**
   * Public endpoint
   */
  @GetMapping
  public ResponseEntity<Object> retrievePaginatedAds(
    @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
    @RequestParam(name = "size", defaultValue = "5") Integer pageSize
  ) {
    return ResponseEntity.ok().body(adService.getAdsWithPagination(pageNumber, pageSize));
  }

  @GetMapping("/search")
  public ResponseEntity<Object> searchBy(
    @RequestParam(required = true) String title,
    @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
    @RequestParam(name = "size", defaultValue = "5") Integer pageSize
  ) {
    return ResponseEntity.ok().body(adService.searchAdsByTitle(title, pageNumber, pageSize));
  }

}
