package com.example.cadastropessoas.infrastructure.repository.pessoafisica.repository;

import com.example.cadastropessoas.infrastructure.repository.pessoafisica.model.PessoaFisicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaModel, Long> {
}
