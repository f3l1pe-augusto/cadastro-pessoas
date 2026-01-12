package com.example.cadastropessoas.domain.pessoa.exception;

import com.example.cadastropessoas.domain.core.exception.DomainException;
import com.example.cadastropessoas.domain.core.statics.DomainErrorCodes;

public class PessoaNaoEncontradaException extends DomainException {
  public PessoaNaoEncontradaException() {
    super(
        DomainErrorCodes.NOT_FOUND,
        "Pessoa não encontrada."
    );
  }
}
