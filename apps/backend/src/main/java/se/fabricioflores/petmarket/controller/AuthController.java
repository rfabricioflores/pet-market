package se.fabricioflores.petmarket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import se.fabricioflores.petmarket.dto.RegisterCredentials;
import se.fabricioflores.petmarket.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @Valid RegisterCredentials credentials) {
    return ResponseEntity.ok().body(userService.registerUser(credentials));
  }

}
