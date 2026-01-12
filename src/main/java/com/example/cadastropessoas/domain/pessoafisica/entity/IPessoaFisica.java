package com.example.cadastropessoas.domain.pessoafisica.entity;

import java.time.LocalDate;

public interface IPessoaFisica {
  String getCpf();
  LocalDate getDataNascimento();
}
