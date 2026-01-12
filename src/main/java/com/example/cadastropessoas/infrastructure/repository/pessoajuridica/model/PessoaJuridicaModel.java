package com.example.cadastropessoas.infrastructure.repository.pessoajuridica.model;

import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.infrastructure.repository.pessoa.model.PessoaModel;
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
@Table(name = "pessoa_juridica")
public class PessoaJuridicaModel implements IPessoaJuridica {

  @Id
  @Basic(optional = false)
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
  private PessoaModel pessoa;

  @Column(name = "cnpj", unique = true, nullable = false)
  private String cnpj;

  @Column(name = "razao_social", nullable = false)
  private String razaoSocial;
}
