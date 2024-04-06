package br.com.lucas.vacancymanagement.modules.companies.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.modules.candidates.exceptions.UserFoundException;
import br.com.lucas.vacancymanagement.modules.companies.entities.CompanyEntity;
import br.com.lucas.vacancymanagement.modules.companies.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository
      .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
      .ifPresent(user -> {
        throw new UserFoundException();
      });

    return this.companyRepository.save(companyEntity);
  }
}