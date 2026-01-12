package com.example.cadastropessoas.infrastructure.mapper.pessoafisica;

import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.infrastructure.repository.pessoafisica.dto.PessoaFisicaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PessoaFisicaMapper {
  PessoaFisicaDTO toDTO(IPessoaFisica pessoaFisica);
}
