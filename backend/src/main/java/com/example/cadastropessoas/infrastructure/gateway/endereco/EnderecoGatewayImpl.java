package com.example.cadastropessoas.infrastructure.gateway.endereco;

import com.example.cadastropessoas.domain.endereco.entity.IEndereco;
import com.example.cadastropessoas.domain.endereco.exception.EnderecoNaoEncontradoException;
import com.example.cadastropessoas.domain.endereco.gateway.EnderecoGateway;
import com.example.cadastropessoas.infrastructure.repository.endereco.model.EnderecoModel;
import com.example.cadastropessoas.infrastructure.repository.endereco.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnderecoGatewayImpl implements EnderecoGateway {

  private final EnderecoRepository enderecoRepository;

  @Override
  public IEndereco obterPorId(Long id) throws EnderecoNaoEncontradoException {
    if (id == null) {
      return new EnderecoModel();
    }

    return enderecoRepository.findById(id).orElseThrow(EnderecoNaoEncontradoException::new);
  }
}
