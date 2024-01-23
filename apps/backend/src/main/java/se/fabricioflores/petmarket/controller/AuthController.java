package se.fabricioflores.petmarket.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import se.fabricioflores.petmarket.config.JwtProvider;
import se.fabricioflores.petmarket.dto.LoginCredentials;
import se.fabricioflores.petmarket.dto.RegisterCredentials;
import se.fabricioflores.petmarket.model.User;
import se.fabricioflores.petmarket.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

  private final UserService userService;
  private final JwtProvider jwtProvider;

  public AuthController(UserService userService, JwtProvider jwtProvider) {
    this.userService = userService;
    this.jwtProvider = jwtProvider;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @Valid RegisterCredentials credentials) {
    return ResponseEntity.ok().body(userService.registerUser(credentials));
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid LoginCredentials credentials) {
    User user = userService.loadUserByUsername(credentials.username());

    return ResponseEntity.ok().body(Map.of("token", jwtProvider.generateToken(user)));
  }
}
