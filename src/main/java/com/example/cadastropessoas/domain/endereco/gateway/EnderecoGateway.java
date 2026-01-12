package com.example.cadastropessoas.domain.endereco.gateway;

import com.example.cadastropessoas.domain.endereco.entity.IEndereco;
import com.example.cadastropessoas.domain.endereco.exception.EnderecoNaoEncontradoException;

public interface EnderecoGateway {
  IEndereco obterPorId(Long id) throws EnderecoNaoEncontradoException;
}
