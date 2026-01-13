package com.example.cadastropessoas.domain.telefone.exception;

import com.example.cadastropessoas.domain.core.exception.DomainException;
import com.example.cadastropessoas.domain.core.statics.DomainErrorCodes;

public class TelefoneNaoEncontradoException extends DomainException {
  public TelefoneNaoEncontradoException() {
    super(
        DomainErrorCodes.NOT_FOUND,
        "Telefone não encontrado."
    );
  }
}
