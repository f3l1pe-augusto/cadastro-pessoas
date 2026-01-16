package com.example.cadastropessoas.infrastructure.repository.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarEnderecoDTO {
  private Long id;
  private String logradouro;
  private String numero;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;
  private String complemento;
}
