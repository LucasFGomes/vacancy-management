package br.com.lucas.vacancymanagement.modules.companies.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
  private String username;
  private String password;
}
