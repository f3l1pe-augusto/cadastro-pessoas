package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListarPessoasUseCase {

  private final PessoaGateway pessoaGateway;

  public Output executar() {
    Output output = new Output();
    output.setPessoas(pessoaGateway.listar());

    return output;
  }

  @Getter
  @Setter
  public static class Output {
    private List<IPessoa> pessoas;
  }
}
