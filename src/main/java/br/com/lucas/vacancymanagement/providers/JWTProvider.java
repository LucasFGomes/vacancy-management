package br.com.lucas.vacancymanagement.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {

  @Value("${security.token.secret}")
  private String secretKey;
  
  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer ", "");

    Algorithm algoritm = Algorithm.HMAC256(secretKey);

    try {
      return JWT.require(algoritm).build().verify(token);
    } catch(JWTVerificationException e) {
      e.printStackTrace();
      return null;
    }
  }
}
