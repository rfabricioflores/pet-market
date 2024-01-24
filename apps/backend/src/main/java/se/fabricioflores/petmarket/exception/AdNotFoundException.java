package se.fabricioflores.petmarket.exception;

public class AdNotFoundException extends RuntimeException {
  public AdNotFoundException() {
    super("Ad not found");
  }
}
