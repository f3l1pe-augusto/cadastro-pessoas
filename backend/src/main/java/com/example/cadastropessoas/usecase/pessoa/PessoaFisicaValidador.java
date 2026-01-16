package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaValidador;
import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoafisica.gateway.PessoaFisicaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PessoaFisicaValidador implements PessoaValidador {

  private final PessoaFisicaGateway pessoaFisicaGateway;

  @Override
  public boolean aceita(IPessoa pessoa) {
    return pessoa.getPessoaFisica() != null;
  }

  @Override
  public void validar(IPessoa pessoa) throws InputInvalidoException {
    IPessoaFisica pessoaFisica = pessoa.getPessoaFisica();

    if (pessoaFisica.getCpf() == null || pessoaFisica.getCpf().isBlank()) {
      throw new InputInvalidoException("CPF inválido.");
    }

    if (pessoaFisicaGateway.cpfExisteParaOutraPessoa(pessoaFisica.getCpf(), pessoaFisica.getId())) {
      throw new InputInvalidoException("CPF já cadastrado.");
    }

    if (pessoaFisica.getDataNascimento() == null) {
      throw new InputInvalidoException("Data de nascimento inválida.");
    }
  }
}
