package com.example.cadastropessoas.domain.core.statics;

public class DomainErrorCodes {
  public static final int NOT_FOUND = 404;
  public static final int INVALID_DATA = 400;

  private DomainErrorCodes() {
    throw new IllegalStateException("Utility class");
  }
}
