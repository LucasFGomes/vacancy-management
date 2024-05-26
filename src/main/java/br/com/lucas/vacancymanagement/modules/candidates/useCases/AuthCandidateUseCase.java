package br.com.lucas.vacancymanagement.modules.candidates.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.lucas.vacancymanagement.modules.candidates.dtos.AuthCandidateRequestDTO;
import br.com.lucas.vacancymanagement.modules.candidates.dtos.AuthCandidateResponseDTO;
import br.com.lucas.vacancymanagement.modules.candidates.repositories.CandidateRepository;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CandidateRepository candidateRepository;
  
  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateDTO) throws AuthenticationException {
    var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username())
                      .orElseThrow(() -> {
                        throw new UsernameNotFoundException("Username/password incorrect");
                      });

    var passwordMatch = this.passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

    if (!passwordMatch) {
      throw new AuthenticationException();
    }
    
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofHours(2));
    var token = JWT.create()
                  .withIssuer("javagas")
                  .withSubject(candidate.getId().toString())
                  .withClaim("roles", Arrays.asList("CANDIDATE"))
                  .withExpiresAt(expiresIn)
                  .sign(algorithm);
  
    var authCandidateResponse = AuthCandidateResponseDTO.builder()
                                                        .access_token(token)
                                                        .expires_in(expiresIn.toEpochMilli())
                                                        .build();

    return authCandidateResponse;
  }
}
