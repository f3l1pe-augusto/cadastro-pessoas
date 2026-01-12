package com.example.cadastropessoas.domain.pessoa.entity;

import com.example.cadastropessoas.domain.endereco.entity.SalvarEndereco;
import com.example.cadastropessoas.domain.telefone.entity.SalvarTelefone;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalvarPessoa {
  private Long id;
  private String nome;
  private String email;
  private Long idPessoaFisica;
  private Long idPessoaJuridica;
  private List<SalvarEndereco> enderecos;
  private List<SalvarTelefone> telefones;
}
