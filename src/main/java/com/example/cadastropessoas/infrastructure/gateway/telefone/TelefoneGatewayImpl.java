package com.example.cadastropessoas.infrastructure.gateway.telefone;

import com.example.cadastropessoas.domain.telefone.entity.ITelefone;
import com.example.cadastropessoas.domain.telefone.exception.TelefoneNaoEncontradoException;
import com.example.cadastropessoas.domain.telefone.gateway.TelefoneGateway;
import com.example.cadastropessoas.infrastructure.repository.telefone.model.TelefoneModel;
import com.example.cadastropessoas.infrastructure.repository.telefone.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TelefoneGatewayImpl implements TelefoneGateway {

  private final TelefoneRepository telefoneRepository;

  @Override
  public ITelefone obterPorId(Long id) throws TelefoneNaoEncontradoException {
    if (id == null) {
      return new TelefoneModel();
    }

    return telefoneRepository.findById(id).orElseThrow(TelefoneNaoEncontradoException::new);
  }
}
