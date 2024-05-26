package br.com.lucas.vacancymanagement.modules.companies.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.companies.dtos.CreateJobDTO;
import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;
import br.com.lucas.vacancymanagement.modules.companies.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/jobs")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;
  
  @PostMapping("/")
  public JobEntity create(@Valid @RequestBody @NonNull CreateJobDTO createJobDTO, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");

    var jobEntity = JobEntity.builder()
                            .description(createJobDTO.getDescription())
                            .benefits(createJobDTO.getBenefits())
                            .level(createJobDTO.getLevel())
                            .companyId(UUID.fromString(companyId.toString()))
                            .build();

    return this.createJobUseCase.execute(jobEntity);
  }
}
