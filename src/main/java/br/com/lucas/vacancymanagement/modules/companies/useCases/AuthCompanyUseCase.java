package br.com.lucas.vacancymanagement.modules.companies.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.lucas.vacancymanagement.modules.companies.dtos.AuthCompanyDTO;
import br.com.lucas.vacancymanagement.modules.companies.dtos.AuthCompanyResponseDTO;
import br.com.lucas.vacancymanagement.modules.companies.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CompanyRepository companyRepository;

  public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
      () -> {
        throw new UsernameNotFoundException("Username or password incorrect");
      }
    );
  
    var passwordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatch) {
      throw new AuthenticationException();
    }

    Algorithm algoritm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofHours(2));

    var token = JWT.create().withIssuer("javagas")
                    .withSubject(company.getId().toString())
                    .withClaim("roles", Arrays.asList("COMPANY"))
                    .withExpiresAt(expiresIn)
                    .sign(algoritm);

    var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                                                      .access_token(token)
                                                      .expires_in(expiresIn.toEpochMilli())
                                                      .build();

    return authCompanyResponseDTO;
  }
}
