package com.example.cadastropessoas.infrastructure.gateway.pessoa;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.exception.PessoaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaGateway;
import com.example.cadastropessoas.infrastructure.repository.pessoa.model.PessoaModel;
import com.example.cadastropessoas.infrastructure.repository.pessoa.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PessoaGatewayImpl implements PessoaGateway {

  private final PessoaRepository pessoaRepository;

  @Override
  public IPessoa obterPorId(Long id) throws PessoaNaoEncontradaException {
    if (id == null) {
      return new PessoaModel();
    }

    return pessoaRepository.findById(id).orElseThrow(PessoaNaoEncontradaException::new);
  }

  @Override
  public IPessoa salvar(IPessoa pessoa) {
    return pessoaRepository.save((PessoaModel) pessoa);
  }

  public List<IPessoa> listar() {
    return List.copyOf(pessoaRepository.findAll());
  }

  @Override
  public void deletar(IPessoa pessoa) {
    pessoaRepository.delete((PessoaModel) pessoa);
  }
}
