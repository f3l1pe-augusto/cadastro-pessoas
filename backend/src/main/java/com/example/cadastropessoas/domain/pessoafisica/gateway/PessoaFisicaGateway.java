package com.example.cadastropessoas.domain.pessoafisica.gateway;

import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoafisica.exception.PessoaFisicaNaoEncontradaException;

public interface PessoaFisicaGateway {
  IPessoaFisica obterPorId(Long id) throws PessoaFisicaNaoEncontradaException;
  boolean cpfExisteParaOutraPessoa(String cpf, Long id);
}
