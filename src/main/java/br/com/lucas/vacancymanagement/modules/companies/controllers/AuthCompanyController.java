package br.com.lucas.vacancymanagement.modules.companies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.companies.dtos.AuthCompanyDTO;
import br.com.lucas.vacancymanagement.modules.companies.dtos.AuthCompanyResponseDTO;
import br.com.lucas.vacancymanagement.modules.companies.useCases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/companies")
@Tag(name = "Autenticação", description = "Requisições para autenticação")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;
  
  @PostMapping("/auth")
  @Operation(summary = "Autenticar uma empresa", description = "Essa função é responsável para autenticar uma empresa")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = AuthCompanyResponseDTO.class))
    }),
    @ApiResponse(responseCode = "401", description = "Não autorizado")
  })
  public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
    try {
      var result = this.authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
