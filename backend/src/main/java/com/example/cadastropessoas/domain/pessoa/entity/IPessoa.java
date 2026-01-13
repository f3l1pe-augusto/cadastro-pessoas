package com.example.cadastropessoas.domain.pessoa.entity;

import com.example.cadastropessoas.domain.endereco.entity.IEndereco;
import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.telefone.entity.ITelefone;

import java.util.Collection;

public interface IPessoa {
  /* Getters */
  Long getId();
  String getNome();
  String getEmail();
  IPessoaFisica getPessoaFisica();
  IPessoaJuridica getPessoaJuridica();

  @SuppressWarnings("java:S1452")
  Collection<? extends IEndereco> getEnderecos();

  @SuppressWarnings("java:S1452")
  Collection<? extends ITelefone> getTelefones();

  /* Setters */
  void setNome(String nome);
  void setEmail(String email);

  /* Applies */
  void applyPessoaFisica(IPessoaFisica pessoaFisica);
  void applyPessoaJuridica(IPessoaJuridica pessoaJuridica);
  void applyEnderecos(Collection<? extends IEndereco> enderecos);
  void applyTelefones(Collection<? extends ITelefone> telefones);
}
