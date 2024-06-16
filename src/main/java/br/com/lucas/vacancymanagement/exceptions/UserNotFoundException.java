package br.com.lucas.vacancymanagement.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("Usuário não existe");
  }
}
