package br.com.lucas.vacancymanagement.modules.candidates.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
  private UUID id;
  private String description;
  private String name;
  private String username;
  private String email;
}
