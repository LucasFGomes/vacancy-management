package br.com.lucas.vacancymanagement.modules.candidates.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("User already exists!");
  }
}
