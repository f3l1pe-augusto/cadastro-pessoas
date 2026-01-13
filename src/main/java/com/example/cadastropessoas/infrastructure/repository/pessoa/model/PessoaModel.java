package com.example.cadastropessoas.infrastructure.repository.pessoa.model;

import com.example.cadastropessoas.domain.endereco.entity.IEndereco;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.telefone.entity.ITelefone;
import com.example.cadastropessoas.infrastructure.repository.endereco.model.EnderecoModel;
import com.example.cadastropessoas.infrastructure.repository.pessoafisica.model.PessoaFisicaModel;
import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.model.PessoaJuridicaModel;
import com.example.cadastropessoas.infrastructure.repository.telefone.model.TelefoneModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@Table(name = "pessoa")
public class PessoaModel implements IPessoa {

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

  @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Collection<EnderecoModel> enderecos;

  @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Collection<TelefoneModel> telefones;

  @Override
  public void applyPessoaFisica(IPessoaFisica pessoaFisica) {
    this.pessoaFisica = (PessoaFisicaModel) pessoaFisica;
  }

  @Override
  public void applyPessoaJuridica(IPessoaJuridica pessoaJuridica) {
    this.pessoaJuridica = (PessoaJuridicaModel) pessoaJuridica;
  }

  @Override
  public void applyEnderecos(Collection<? extends IEndereco> enderecos) {
    this.enderecos = enderecos.stream()
        .map(EnderecoModel.class::cast).collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public void applyTelefones(Collection<? extends ITelefone> telefones) {
    this.telefones = telefones.stream()
        .map(TelefoneModel.class::cast).collect(Collectors.toCollection(ArrayList::new));
  }
}
