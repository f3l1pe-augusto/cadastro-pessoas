package com.example.cadastropessoas.domain.endereco.entity;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;

public interface IEndereco {
  /* Getters */
  Long getId();
  String getLogradouro();
  String getNumero();
  String getBairro();
  String getCidade();
  UF getUf();
  String getCep();
  String getComplemento();

  /* Setters */
  void setLogradouro(String logradouro);
  void setNumero(String numero);
  void setBairro(String bairro);
  void setCidade(String cidade);
  void setUf(UF uf);
  void setCep(String cep);
  void setComplemento(String complemento);

  /* Applies */
  void applyPessoa(IPessoa pessoa);
}
