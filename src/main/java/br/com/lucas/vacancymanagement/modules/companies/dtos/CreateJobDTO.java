package br.com.lucas.vacancymanagement.modules.companies.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
  @Schema(example = "Vaga para desenvolvedora júnior", requiredMode = RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "Plano de Saúde, Day off no mês do aniversário", requiredMode = RequiredMode.REQUIRED)
  private String benefits;

  @Schema(example = "JÚNIOR", requiredMode = RequiredMode.REQUIRED)
  private String level;
}
