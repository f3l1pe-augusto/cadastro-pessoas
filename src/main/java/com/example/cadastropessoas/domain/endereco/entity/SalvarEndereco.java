package com.example.cadastropessoas.domain.endereco.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarEndereco {
  private Long id;
  private String logradouro;
  private String cidade;
  private String estado;
  private String cep;
  private String complemento;
}
