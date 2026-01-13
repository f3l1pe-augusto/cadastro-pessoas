package com.example.cadastropessoas.infrastructure.repository.pessoa.repository;

import com.example.cadastropessoas.infrastructure.repository.pessoa.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}
