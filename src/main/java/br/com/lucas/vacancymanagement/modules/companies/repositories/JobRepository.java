package br.com.lucas.vacancymanagement.modules.companies.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucas.vacancymanagement.modules.companies.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

  List<JobEntity> findByDescriptionContaining(String filter);

}
