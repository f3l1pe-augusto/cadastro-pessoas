package com.example.cadastro_pessoas.infrastructure.repository.pessoa.model;

import com.example.cadastro_pessoas.infrastructure.repository.endereco.model.EnderecoModel;
import com.example.cadastro_pessoas.infrastructure.repository.pessoafisica.model.PessoaFisicaModel;
import com.example.cadastro_pessoas.infrastructure.repository.pessoajuridica.model.PessoaJuridicaModel;
import com.example.cadastro_pessoas.infrastructure.repository.telefone.model.TelefoneModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@Table(name = "pessoa")
public class PessoaModel {

  @Id
  @Basic(optional = false)
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
  private PessoaFisicaModel pessoaFisica;

  @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
  private PessoaJuridicaModel pessoaJuridica;

  @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<EnderecoModel> enderecos;

  @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<TelefoneModel> telefones;
}
