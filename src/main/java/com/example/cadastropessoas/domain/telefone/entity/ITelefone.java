package com.example.cadastropessoas.domain.telefone.entity;

import com.example.cadastropessoas.domain.tipotelefone.entity.TipoTelefone;

public interface ITelefone {
  Long getId();
  String getNumero();
  TipoTelefone getTipoTelefone();
}
