package com.example.cadastropessoas.domain.telefone.gateway;

import com.example.cadastropessoas.domain.telefone.entity.ITelefone;
import com.example.cadastropessoas.domain.telefone.exception.TelefoneNaoEncontradoException;

public interface TelefoneGateway {
  ITelefone obterPorId(Long id) throws TelefoneNaoEncontradoException;
}
