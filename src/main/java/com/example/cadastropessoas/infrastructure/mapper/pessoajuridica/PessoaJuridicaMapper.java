package com.example.cadastropessoas.infrastructure.mapper.pessoajuridica;

import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.infrastructure.repository.pessoajuridica.dto.PessoaJuridicaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PessoaJuridicaMapper {
  PessoaJuridicaDTO toDTO(IPessoaJuridica pessoaJuridica);
}
