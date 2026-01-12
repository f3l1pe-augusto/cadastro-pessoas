package com.example.cadastropessoas.domain.endereco.entity;

public interface IEndereco {
  Long getId();
  String getLogradouro();
  String getCidade();
  String getEstado();
  String getCep();
  String getComplemento();
}
