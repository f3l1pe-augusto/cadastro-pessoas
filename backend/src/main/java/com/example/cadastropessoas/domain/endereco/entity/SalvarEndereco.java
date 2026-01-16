package com.example.cadastropessoas.domain.endereco.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarEndereco {
  private Long id;
  private String logradouro;
  private String numero;
  private String bairro;
  private String cidade;
  private UF uf;
  private String cep;
  private String complemento;
}
