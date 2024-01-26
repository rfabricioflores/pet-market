package se.fabricioflores.petmarket.advice;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import se.fabricioflores.petmarket.exception.AdNotFoundException;
import se.fabricioflores.petmarket.exception.InvalidCredentialsException;
import se.fabricioflores.petmarket.exception.InvalidLoginCredsException;
import se.fabricioflores.petmarket.exception.UserNotFoundException;
import se.fabricioflores.petmarket.exception.UsernameNotAvailableException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({
    UserNotFoundException.class,
    AdNotFoundException.class
  })
  public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException exception) {
    return ResponseEntity.status(404).body(Map.of("error", exception.getMessage()));
  }

  /**
   * Caused by request conflicts with the current state of the target resource,
   * often used when attempting to create an entity that already exists
   */
  @ExceptionHandler({
    UsernameNotAvailableException.class,
    InvalidCredentialsException.class
  })
  public ResponseEntity<Object> handleConflictExceptions(RuntimeException exception) {
    return ResponseEntity.status(409).body(Map.of("error", exception.getMessage()));
  }

  /**
   * Caused by requests with invalid authentication credentials
   */
  @ExceptionHandler({
    InvalidLoginCredsException.class
  })
  public ResponseEntity<Object> handleAuthorizationConflicts(RuntimeException exception) {
    return ResponseEntity.status(401).body(Map.of("error", exception.getMessage()));
  }

}
