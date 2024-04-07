package br.com.lucas.vacancymanagement.modules.companies.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.modules.companies.dtos.AuthCompanyDTO;
import br.com.lucas.vacancymanagement.modules.companies.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CompanyRepository companyRepository;

  public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
      () -> {
        throw new UsernameNotFoundException("Company não encontrado");
      }
    );
  
    var passwordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatch) {
      throw new AuthenticationException();
    }
  }
}
