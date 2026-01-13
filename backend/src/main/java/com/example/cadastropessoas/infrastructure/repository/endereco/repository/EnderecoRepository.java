package com.example.cadastropessoas.infrastructure.repository.endereco.repository;

import com.example.cadastropessoas.infrastructure.repository.endereco.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
}
