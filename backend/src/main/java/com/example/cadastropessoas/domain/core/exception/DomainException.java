package com.example.cadastropessoas.domain.core.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DomainException extends Exception {
  private final int errorCode;

  public DomainException(int errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }
}
