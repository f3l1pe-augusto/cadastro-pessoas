package com.example.cadastropessoas.infrastructure.repository.telefone.repository;

import com.example.cadastropessoas.infrastructure.repository.telefone.model.TelefoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneModel, Long> {
}
