package com.example.cadastropessoas.domain.pessoa.gateway;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.exception.PessoaNaoEncontradaException;

import java.util.List;

public interface PessoaGateway {
  IPessoa obterPorId(Long id) throws PessoaNaoEncontradaException;
  IPessoa salvar(IPessoa pessoa);
  List<IPessoa> listar();
  void deletar(IPessoa pessoa);
}
