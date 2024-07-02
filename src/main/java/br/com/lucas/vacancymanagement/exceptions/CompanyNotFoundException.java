package br.com.lucas.vacancymanagement.exceptions;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException() {
    super("Empresa não existe");
  }
}
