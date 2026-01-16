package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaValidador;
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.pessoajuridica.gateway.PessoaJuridicaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PessoaJuridicaValidador implements PessoaValidador {

  private final PessoaJuridicaGateway pessoaJuridicaGateway;

  @Override
  public boolean aceita(IPessoa pessoa) {
    return pessoa.getPessoaJuridica() != null;
  }

  @Override
  public void validar(IPessoa pessoa) throws InputInvalidoException {
    IPessoaJuridica pessoaJuridica = pessoa.getPessoaJuridica();

    if (pessoaJuridica.getCnpj() == null || pessoaJuridica.getCnpj().isBlank()) {
      throw new InputInvalidoException("CNPJ inválido.");
    }

    if (pessoaJuridicaGateway.cnpjExisteParaOutraPessoa(pessoaJuridica.getCnpj(), pessoaJuridica.getId())) {
      throw new InputInvalidoException("CNPJ já cadastrado.");
    }

    if (pessoaJuridica.getRazaoSocial() == null || pessoaJuridica.getRazaoSocial().isBlank()) {
      throw new InputInvalidoException("Razão social inválida.");
    }
  }
}
