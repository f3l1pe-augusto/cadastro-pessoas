package com.example.cadastro_pessoas.infrastructure.repository.pessoafisica.model;

import com.example.cadastro_pessoas.infrastructure.repository.pessoa.model.PessoaModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisicaModel {

  @Id
  @Basic(optional = false)
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
  private PessoaModel pessoa;

  @Column(name = "cpf", unique = true, nullable = false)
  private String cpf;

  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;
}
