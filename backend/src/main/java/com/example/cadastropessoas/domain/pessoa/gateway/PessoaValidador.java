package com.example.cadastropessoas.domain.pessoa.gateway;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;

public interface PessoaValidador {
  void validar(IPessoa pessoa) throws InputInvalidoException;
  boolean aceita(IPessoa pessoa);
}
