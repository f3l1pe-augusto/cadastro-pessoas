package com.example.cadastropessoas.domain.telefone.entity;

public enum TipoTelefone {
  TELEFONE_RESIDENCIAL {
    @Override
    public String getDescricao() {
      return "Telefone Residencial";
    }
  },
  TELEFONE_COMERCIAL {
    @Override
    public String getDescricao() {
      return "Telefone Comercial";
    }
  },
  TELEFONE_CELULAR {
    @Override
    public String getDescricao() {
      return "Telefone Celular";
    }
  };

  public abstract String getDescricao();
}
