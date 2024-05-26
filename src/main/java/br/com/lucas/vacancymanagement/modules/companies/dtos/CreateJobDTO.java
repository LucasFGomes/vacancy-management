package br.com.lucas.vacancymanagement.modules.companies.dtos;

import lombok.Data;

@Data
public class CreateJobDTO {
  private String description;
  private String benefits;
  private String level;
}
