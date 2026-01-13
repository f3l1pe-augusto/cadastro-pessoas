package com.example.cadastropessoas.infrastructure.repository.pessoajuridica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarPessoaJuridicaDTO {
  private Long id;
  private String cnpj;
  private String razaoSocial;
}
