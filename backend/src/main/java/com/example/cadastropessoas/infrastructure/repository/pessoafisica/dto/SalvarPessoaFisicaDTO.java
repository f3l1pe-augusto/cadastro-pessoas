package com.example.cadastropessoas.infrastructure.repository.pessoafisica.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalvarPessoaFisicaDTO {
  private Long id;
  private String cpf;
  private LocalDate dataNascimento;
}
