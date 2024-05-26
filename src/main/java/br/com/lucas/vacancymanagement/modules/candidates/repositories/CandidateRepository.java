package br.com.lucas.vacancymanagement.modules.candidates.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.vacancymanagement.modules.candidates.entities.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{
  Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
  Optional<CandidateEntity> findByUsername(String username);
}
