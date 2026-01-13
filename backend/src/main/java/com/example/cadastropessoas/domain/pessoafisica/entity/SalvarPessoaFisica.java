package com.example.cadastropessoas.domain.pessoafisica.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalvarPessoaFisica {
  private Long id;
  private String cpf;
  private LocalDate dataNascimento;
}
