package br.com.lucas.vacancymanagement.modules.candidates.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lucas.vacancymanagement.modules.candidates.dtos.ProfileCandidateResponseDTO;
import br.com.lucas.vacancymanagement.modules.candidates.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;
  
  public ProfileCandidateResponseDTO execute(UUID candidateId) {
    var candidate = this.candidateRepository.findById(candidateId)
                                            .orElseThrow(() -> {
                                              throw new UsernameNotFoundException("Candidate not found");
                                            });

    var candidateResponse = ProfileCandidateResponseDTO.builder()
                                                      .id(candidate.getId())
                                                      .name(candidate.getName())
                                                      .email(candidate.getEmail())
                                                      .username(candidate.getUsername())
                                                      .description(candidate.getDescription())
                                                      .build();

    return candidateResponse;
  }
}
