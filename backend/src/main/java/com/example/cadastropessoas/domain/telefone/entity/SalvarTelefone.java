package com.example.cadastropessoas.domain.telefone.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarTelefone {
  private Long id;
  private String numero;
  private TipoTelefone tipoTelefone;
}
