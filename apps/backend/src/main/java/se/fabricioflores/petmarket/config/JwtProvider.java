package se.fabricioflores.petmarket.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import se.fabricioflores.petmarket.dto.TokenResDto;
import se.fabricioflores.petmarket.dto.UserDto;

@Component
public class JwtProvider {

  @Value("${jwt.secret-key}")
  private String jwtSecret;

  public TokenResDto generateToken(UserDto user) {
    long nowMillis = System.currentTimeMillis();

    Date now = new Date(nowMillis);
    Date exp = new Date(nowMillis + TimeUnit.MINUTES.toMillis(5));

    String token = Jwts
            .builder()
            .setSubject(user.username())
            .claim("id", user.id())
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();

    return new TokenResDto(token, exp, user);
  }

  public Claims validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
    return Jwts
            .parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();
  }

}
