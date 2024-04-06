package br.com.lucas.vacancymanagement.modules.companies.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;
import br.com.lucas.vacancymanagement.modules.companies.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;
  
  public JobEntity execute(JobEntity jobEntity) {
    return this.jobRepository.save(jobEntity);
  }
}
