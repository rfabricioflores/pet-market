package se.fabricioflores.petmarket.exception;

public class InvalidLoginCredsException extends RuntimeException {
  public InvalidLoginCredsException() {
    super("Invalid login credentials");
  }
}
