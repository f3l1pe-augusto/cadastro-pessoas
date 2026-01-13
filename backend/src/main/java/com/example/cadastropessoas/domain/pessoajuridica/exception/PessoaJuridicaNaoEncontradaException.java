package com.example.cadastropessoas.domain.pessoajuridica.exception;

import com.example.cadastropessoas.domain.core.exception.DomainException;
import com.example.cadastropessoas.domain.core.statics.DomainErrorCodes;

public class PessoaJuridicaNaoEncontradaException extends DomainException {
  public PessoaJuridicaNaoEncontradaException() {
    super(
        DomainErrorCodes.NOT_FOUND,
        "Pessoa Jurídica não encontrada."
    );
  }
}
