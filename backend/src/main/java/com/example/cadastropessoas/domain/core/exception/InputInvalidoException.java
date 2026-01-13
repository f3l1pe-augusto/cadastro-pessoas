package com.example.cadastropessoas.domain.core.exception;

import com.example.cadastropessoas.domain.core.statics.DomainErrorCodes;

public class InputInvalidoException extends DomainException {
  public InputInvalidoException(String message) {
    super(
        DomainErrorCodes.INVALID_DATA,
        message
    );
  }
}
