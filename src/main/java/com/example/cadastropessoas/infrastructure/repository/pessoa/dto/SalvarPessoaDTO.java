package com.example.cadastropessoas.infrastructure.repository.pessoa.dto;

import com.example.cadastropessoas.infrastructure.repository.endereco.dto.SalvarEnderecoDTO;
import com.example.cadastropessoas.infrastructure.repository.telefone.dto.SalvarTelefoneDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SalvarPessoaDTO {
  private Long id;
  private String nome;
  private String email;
  private Long idPessoaFisica;
  private Long idPessoaJuridica;
  private List<SalvarEnderecoDTO> enderecos;
  private List<SalvarTelefoneDTO> telefones;
}
