package com.example.cadastropessoas.domain.pessoafisica.exception;

import com.example.cadastropessoas.domain.core.exception.DomainException;
import com.example.cadastropessoas.domain.core.statics.DomainErrorCodes;

public class PessoaFisicaNaoEncontradaException extends DomainException {
  public PessoaFisicaNaoEncontradaException() {
    super(
        DomainErrorCodes.NOT_FOUND,
        "Pessoa Física não encontrada."
    );
  }
}
