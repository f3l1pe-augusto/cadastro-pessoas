package com.example.cadastropessoas.domain.pessoajuridica.gateway;

import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.pessoajuridica.exception.PessoaJuridicaNaoEncontradaException;

public interface PessoaJuridicaGateway {
  IPessoaJuridica obterPorId(Long id) throws PessoaJuridicaNaoEncontradaException;
}
