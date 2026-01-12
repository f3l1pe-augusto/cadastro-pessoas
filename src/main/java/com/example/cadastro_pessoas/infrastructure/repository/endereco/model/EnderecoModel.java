package com.example.cadastro_pessoas.infrastructure.repository.endereco.model;

import com.example.cadastro_pessoas.infrastructure.repository.pessoa.model.PessoaModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@Table(name = "endereco")
public class EnderecoModel {

  @Id
  @Basic(optional = false)
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
  private PessoaModel pessoa;

  @Column(name = "logradouro", nullable = false)
  private String logradouro;

  @Column(name = "cidade", nullable = false)
  private String cidade;

  @Column(name = "estado", nullable = false)
  private String estado;

  @Column(name = "cep", nullable = false)
  private String cep;

  @Column(name = "complemento")
  private String complemento;
}
