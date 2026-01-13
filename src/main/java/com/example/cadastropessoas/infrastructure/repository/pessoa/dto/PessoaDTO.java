package com.example.cadastropessoas.infrastructure.repository.pessoa.dto;

import com.example.cadastropessoas.infrastructure.repository.pessoafisica.dto.PessoaFisicaDTO;
import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.dto.PessoaJuridicaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
  private Long id;
  private String nome;
  private String email;
  private PessoaFisicaDTO pessoaFisica;
  private PessoaJuridicaDTO pessoaJuridica;
}
