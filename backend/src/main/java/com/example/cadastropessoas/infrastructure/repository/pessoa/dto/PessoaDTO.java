package com.example.cadastropessoas.infrastructure.repository.pessoa.dto;

import com.example.cadastropessoas.infrastructure.repository.endereco.dto.EnderecoDTO;
import com.example.cadastropessoas.infrastructure.repository.pessoafisica.dto.PessoaFisicaDTO;
import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.dto.PessoaJuridicaDTO;
import com.example.cadastropessoas.infrastructure.repository.telefone.dto.TelefoneDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaDTO {
  private Long id;
  private String nome;
  private String email;
  private PessoaFisicaDTO pessoaFisica;
  private PessoaJuridicaDTO pessoaJuridica;
  private List<EnderecoDTO> enderecos;
  private List<TelefoneDTO> telefones;
}
