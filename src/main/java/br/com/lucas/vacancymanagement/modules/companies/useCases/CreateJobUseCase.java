package br.com.lucas.vacancymanagement.modules.companies.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.exceptions.CompanyNotFoundException;
import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;
import br.com.lucas.vacancymanagement.modules.companies.repositories.CompanyRepository;
import br.com.lucas.vacancymanagement.modules.companies.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;
  
  public JobEntity execute(JobEntity jobEntity) {
    this.companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
      throw new CompanyNotFoundException();
    });
    return this.jobRepository.save(jobEntity);
  }
}
