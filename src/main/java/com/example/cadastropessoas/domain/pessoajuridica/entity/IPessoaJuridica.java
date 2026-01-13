package com.example.cadastropessoas.domain.pessoajuridica.entity;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;

public interface IPessoaJuridica {
  /* Getters */
  String getCnpj();
  String getRazaoSocial();

  /* Setters */
  void setCnpj(String cnpj);
  void setRazaoSocial(String razaoSocial);

  /* Applies */
  void applyPessoa(IPessoa pessoa);
}
