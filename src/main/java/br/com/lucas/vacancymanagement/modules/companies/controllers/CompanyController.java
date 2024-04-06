package br.com.lucas.vacancymanagement.modules.companies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.companies.entities.CompanyEntity;
import br.com.lucas.vacancymanagement.modules.companies.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/companies")
public class CompanyController {
  
  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;
  
  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody @NonNull CompanyEntity companyEntity) {
    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
