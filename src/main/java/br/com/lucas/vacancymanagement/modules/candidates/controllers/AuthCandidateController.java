package br.com.lucas.vacancymanagement.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.candidates.dtos.AuthCandidateRequestDTO;
import br.com.lucas.vacancymanagement.modules.candidates.dtos.AuthCandidateResponseDTO;
import br.com.lucas.vacancymanagement.modules.candidates.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/candidates")
@Tag(name = "Autenticação", description = "Requisições para autenticação")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;
  
  @PostMapping("/auth")
  @Operation(summary = "Autenticar um candidato", description = "Essa função é responsável para autenticar um candidato")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class))
    }),
    @ApiResponse(responseCode = "401", description = "Não autorizado")
  })
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
      return ResponseEntity.ok().body(token);
    } catch(Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
