package se.fabricioflores.petmarket.exception;

public class UsernameNotAvailable extends RuntimeException {
  public UsernameNotAvailable() {
    super("Username is not available");
  }
}
