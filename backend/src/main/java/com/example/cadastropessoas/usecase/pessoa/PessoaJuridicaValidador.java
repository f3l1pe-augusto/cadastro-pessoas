package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaValidador;
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaValidador implements PessoaValidador {

  @Override
  public boolean aceita(IPessoa pessoa) {
    return pessoa instanceof IPessoaJuridica;
  }

  @Override
  public void validar(IPessoa pessoa) throws InputInvalidoException {
    IPessoaJuridica pessoaJuridica = (IPessoaJuridica) pessoa;

    if (pessoaJuridica.getCnpj() == null || pessoaJuridica.getCnpj().isBlank()) {
      throw new InputInvalidoException("CNPJ inválido.");
    }

    if (pessoaJuridica.getRazaoSocial() == null || pessoaJuridica.getRazaoSocial().isBlank()) {
      throw new InputInvalidoException("Razão social inválida.");
    }
  }
}
