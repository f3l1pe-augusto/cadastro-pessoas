package com.example.cadastropessoas.infrastructure.repository.pessoajuridica.repository;

import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.model.PessoaJuridicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaModel, Long> {
  boolean existsByCnpjAndIdNot(String cnpj, Long id);
}
