package com.example.cadastropessoas.domain.endereco.exception;

import com.example.cadastropessoas.domain.core.exception.DomainException;
import com.example.cadastropessoas.domain.core.statics.DomainErrorCodes;

public class EnderecoNaoEncontradoException extends DomainException {
  public EnderecoNaoEncontradoException() {
    super(
        DomainErrorCodes.NOT_FOUND,
        "Endereço não encontrado."
    );
  }
}
