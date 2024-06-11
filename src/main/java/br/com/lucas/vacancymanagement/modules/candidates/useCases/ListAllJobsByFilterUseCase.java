package br.com.lucas.vacancymanagement.modules.candidates.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;
import br.com.lucas.vacancymanagement.modules.companies.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
  }
}