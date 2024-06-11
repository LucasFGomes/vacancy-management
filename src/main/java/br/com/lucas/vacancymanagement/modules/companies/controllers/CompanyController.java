package br.com.lucas.vacancymanagement.modules.companies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.companies.entities.CompanyEntity;
import br.com.lucas.vacancymanagement.modules.companies.useCases.CreateCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/companies")
@Tag(name = "Empresa", description = "Requisições das empresas")
public class CompanyController {
  
  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;
  
  @PostMapping("/")
  @Operation(summary = "Cadastrar uma empresa", description = "Essa função é responsável para cadastrar uma empresa")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = CompanyEntity.class))
    }),
    @ApiResponse(responseCode = "400", description = "Empresa já existe")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody @NonNull CompanyEntity companyEntity) {
    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
