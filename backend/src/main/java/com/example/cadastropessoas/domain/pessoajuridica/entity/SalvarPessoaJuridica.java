package com.example.cadastropessoas.domain.pessoajuridica.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarPessoaJuridica {
  private Long id;
  private String cnpj;
  private String razaoSocial;
}
