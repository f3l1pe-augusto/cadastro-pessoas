package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeletarPessoaUseCase {

  private final PessoaGateway pessoaGateway;

  public void executar(Input input) throws InputInvalidoException {
    validarInput(input);

    pessoaGateway.deletar(input.getPessoa());
  }

  private void validarInput(Input input) throws InputInvalidoException {
    if (input.getPessoa() == null || input.getPessoa().getId() == null || input.getPessoa().getId() <= 0) {
      throw new InputInvalidoException("Pessoa inválida.");
    }

  }

  @Getter
  @Setter
  public static class Input {
    private IPessoa pessoa;
  }
}
