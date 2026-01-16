package com.example.cadastropessoas.infrastructure.gateway.pessoajuridica;

import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.pessoajuridica.exception.PessoaJuridicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoajuridica.gateway.PessoaJuridicaGateway;
import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.model.PessoaJuridicaModel;
import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.repository.PessoaJuridicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PessoaJuridicaGatewayImpl implements PessoaJuridicaGateway {

  private final PessoaJuridicaRepository pessoaJuridicaRepository;

  @Override
  public IPessoaJuridica obterPorId(Long id) throws PessoaJuridicaNaoEncontradaException {
    if (id == null) {
      return new PessoaJuridicaModel();
    }

    return pessoaJuridicaRepository.findById(id).orElseThrow(PessoaJuridicaNaoEncontradaException::new);
  }

  @Override
  public boolean cnpjExisteParaOutraPessoa(String cpf, Long id) {
    return pessoaJuridicaRepository.existsByCnpjAndIdNot(cpf, id);
  }
}
