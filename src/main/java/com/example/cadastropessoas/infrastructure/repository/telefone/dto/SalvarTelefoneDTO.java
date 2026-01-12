package com.example.cadastropessoas.infrastructure.repository.telefone.dto;

import com.example.cadastropessoas.domain.tipotelefone.entity.TipoTelefone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarTelefoneDTO {
  private Long id;
  private String numero;
  private TipoTelefone tipoTelefone;
}
