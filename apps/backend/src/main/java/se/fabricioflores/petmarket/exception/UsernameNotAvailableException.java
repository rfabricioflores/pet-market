package se.fabricioflores.petmarket.exception;

public class UsernameNotAvailableException extends RuntimeException {
  public UsernameNotAvailableException() {
    super("Username is not available");
  }
}
