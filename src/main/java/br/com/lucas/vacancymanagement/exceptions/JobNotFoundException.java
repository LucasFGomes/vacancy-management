package br.com.lucas.vacancymanagement.exceptions;

public class JobNotFoundException extends RuntimeException {
  public JobNotFoundException() {
    super("Job não existe");
  }
}
