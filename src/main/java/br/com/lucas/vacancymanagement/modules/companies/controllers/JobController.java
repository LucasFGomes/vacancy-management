package br.com.lucas.vacancymanagement.modules.companies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;
import br.com.lucas.vacancymanagement.modules.companies.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/jobs")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;
  
  @PostMapping("/")
  public JobEntity create(@Valid @RequestBody @NonNull JobEntity jobEntity) {
    return this.createJobUseCase.execute(jobEntity);
  }
}
