package com.example.cadastropessoas.infrastructure.gateway.pessoafisica;

import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoafisica.exception.PessoaFisicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoafisica.gateway.PessoaFisicaGateway;
import com.example.cadastropessoas.infrastructure.repository.pessoafisica.model.PessoaFisicaModel;
import com.example.cadastropessoas.infrastructure.repository.pessoafisica.repository.PessoaFisicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PessoaFisicaGatewayImpl implements PessoaFisicaGateway {

  private final PessoaFisicaRepository pessoaFisicaRepository;

  @Override
  public IPessoaFisica obterPorId(Long id) throws PessoaFisicaNaoEncontradaException {
    if (id == null) {
      return new PessoaFisicaModel();
    }

    return pessoaFisicaRepository.findById(id).orElseThrow(PessoaFisicaNaoEncontradaException::new);
  }
}
