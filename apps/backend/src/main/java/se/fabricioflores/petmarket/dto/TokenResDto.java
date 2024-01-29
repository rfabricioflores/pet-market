package se.fabricioflores.petmarket.dto;

import java.util.Date;

public record TokenResDto(
  String token,
  Date expDate,
  UserDto user
) {
}
