package br.com.lucas.vacancymanagement.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.exceptions.JobNotFoundException;
import br.com.lucas.vacancymanagement.exceptions.UserNotFoundException;
import br.com.lucas.vacancymanagement.modules.candidates.entities.ApplyJobEntity;
import br.com.lucas.vacancymanagement.modules.candidates.repositories.ApplyJobRepository;
import br.com.lucas.vacancymanagement.modules.candidates.repositories.CandidateRepository;
import br.com.lucas.vacancymanagement.modules.companies.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private ApplyJobRepository applyJobRepository;
  
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
    //Validar se o usuário existe
    this.candidateRepository.findById(candidateId).orElseThrow(() -> {
      throw new UserNotFoundException();
    });
    //Validar se a vaga existe
    this.jobRepository.findById(jobId).orElseThrow(() -> {
      throw new JobNotFoundException();
    });
    //Candidato se inscrever na vaga
    var applyJob = ApplyJobEntity.builder()
                                 .candidateId(candidateId)
                                 .jobId(jobId)
                                 .build();
                                 
    applyJob = this.applyJobRepository.save(applyJob);

    return applyJob;
  }
}
