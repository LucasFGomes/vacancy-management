package br.com.lucas.vacancymanagement.modules.candidates.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  @Schema(example = "Lucas Ferreira", requiredMode = RequiredMode.REQUIRED)
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
  @Schema(example = "lucasf", requiredMode = RequiredMode.REQUIRED)
  private String username;

  @NotBlank
  @Email(message = "O campo [email] deve conter um e-mail válido")
  @Schema(example = "lucasf@gmail.com", requiredMode = RequiredMode.REQUIRED)
  private String email;

  @NotBlank
  @Length(min = 5, max = 100, message = "A senha deve conter entre 5 e 100 caracteres")
  @Schema(example = "admin@12345", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED)
  private String password;

  @Schema(example = "Desenvolvedor Java")
  private String description;

  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
