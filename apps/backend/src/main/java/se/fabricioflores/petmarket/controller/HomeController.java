package se.fabricioflores.petmarket.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

  @Value("${cors.allowed.origin}")
  private String corsAllowedOrigin;

  @GetMapping()
  public ResponseEntity<Object> home() {
    return ResponseEntity.ok().body(Map.of("response", "Welcome to the official api for " + corsAllowedOrigin));
  }
}
