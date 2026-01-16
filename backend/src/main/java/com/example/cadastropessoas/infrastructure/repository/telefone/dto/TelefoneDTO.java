package com.example.cadastropessoas.infrastructure.repository.telefone.dto;

import com.example.cadastropessoas.domain.telefone.entity.TipoTelefone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneDTO {
  private Long id;
  private String numero;
  private TipoTelefone tipoTelefone;
}
