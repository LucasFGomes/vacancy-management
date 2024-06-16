package br.com.lucas.vacancymanagement.modules.candidates.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.vacancymanagement.modules.candidates.entities.ApplyJobEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
  
}
