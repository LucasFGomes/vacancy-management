package br.com.lucas.vacancymanagement.modules.candidates.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.modules.candidates.entities.CandidateEntity;
import br.com.lucas.vacancymanagement.modules.candidates.exceptions.UserFoundException;
import br.com.lucas.vacancymanagement.modules.candidates.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;
  
  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
      .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
      .ifPresent((user) -> {
        throw new UserFoundException();
      });
  
    return this.candidateRepository.save(candidateEntity);
  }
}
