package com.example.cadastropessoas.domain.pessoafisica.entity;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;

import java.time.LocalDate;

public interface IPessoaFisica {
  /* Getters */
  Long getId();
  String getCpf();
  LocalDate getDataNascimento();

  /* Setters */
  void setCpf(String cpf);
  void setDataNascimento(LocalDate dataNascimento);

  /* Applies */
  void applyPessoa(IPessoa pessoa);
}
