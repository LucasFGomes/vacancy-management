package br.com.lucas.vacancymanagement.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("Usuário já existe");
  }
}
