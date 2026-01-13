package com.example.cadastropessoas.domain.telefone.entity;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.tipotelefone.entity.TipoTelefone;

public interface ITelefone {
  /* Getters */
  Long getId();
  String getNumero();
  TipoTelefone getTipoTelefone();

  /* Setters */
  void setNumero(String numero);
  void setTipoTelefone(TipoTelefone tipoTelefone);

  /* Applies */
  void applyPessoa(IPessoa pessoa);
}
