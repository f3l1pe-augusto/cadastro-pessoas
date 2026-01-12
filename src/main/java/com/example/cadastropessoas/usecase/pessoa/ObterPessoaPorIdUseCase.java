package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.exception.PessoaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObterPessoaPorIdUseCase {

  private final PessoaGateway pessoaGateway;

  public Output executar(Input input) throws PessoaNaoEncontradaException, InputInvalidoException {
    validar(input);

    IPessoa pessoa = pessoaGateway.obterPorId(input.getId());

    Output output = new Output();
    output.setPessoa(pessoa);

    return output;
  }

  private void validar(Input input) throws InputInvalidoException {
    if (input == null || input.getId() == null || input.getId() <= 0) {
      throw new InputInvalidoException("ID da pessoa inválido.");
    }
  }

  @Getter
  @Setter
  public static class Input {
    private Long id;
  }

  @Getter
  @Setter
  public static class Output {
    private IPessoa pessoa;
  }
}
