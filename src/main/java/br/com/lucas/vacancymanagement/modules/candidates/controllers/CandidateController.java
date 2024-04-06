package br.com.lucas.vacancymanagement.modules.candidates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.vacancymanagement.modules.candidates.entities.CandidateEntity;
import br.com.lucas.vacancymanagement.modules.candidates.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import lombok.NonNull;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;
  
  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody @NonNull CandidateEntity candidateEntity) {
    try {
      var result = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  
}
