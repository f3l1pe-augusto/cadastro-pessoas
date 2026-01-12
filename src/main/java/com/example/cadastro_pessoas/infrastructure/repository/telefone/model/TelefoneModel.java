package com.example.cadastro_pessoas.infrastructure.repository.telefone.model;

import com.example.cadastro_pessoas.domain.tipotelefone.entity.TipoTelefone;
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
@Table(name = "telefone")
public class TelefoneModel {

  @Id
  @Basic(optional = false)
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
  private PessoaModel pessoa;

  @Column(name = "numero", nullable = false)
  private String numero;

  @Column(name = "tipo_telefone", nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoTelefone tipoTelefone;
}
