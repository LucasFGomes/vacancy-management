package br.com.lucas.vacancymanagement.modules.companies.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.companies.dtos.CreateJobDTO;
import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;
import br.com.lucas.vacancymanagement.modules.companies.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/companies/jobs")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;
  
  @PostMapping("/")
  @PreAuthorize("hasRole('COMPANY')")
  @Tag(name = "Vaga", description = "Requisições das Vagas")
  @Operation(summary = "Criar vagas", description = "Essa função é responsável para criar as vagas")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = JobEntity.class))
    })
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> create(@Valid @RequestBody @NonNull CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    try {
      var jobEntity = JobEntity.builder()
                              .description(createJobDTO.getDescription())
                              .benefits(createJobDTO.getBenefits())
                              .level(createJobDTO.getLevel())
                              .companyId(UUID.fromString(companyId.toString()))
                              .build();
  
      var result = this.createJobUseCase.execute(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}
