package com.example.cadastropessoas.infrastructure.mapper.pessoa;

import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.entity.SalvarPessoa;
import com.example.cadastropessoas.infrastructure.mapper.pessoafisica.PessoaFisicaMapper;
import com.example.cadastropessoas.infrastructure.mapper.pessoajuridica.PessoaJuridicaMapper;
import com.example.cadastropessoas.infrastructure.repository.pessoa.dto.PessoaDTO;
import com.example.cadastropessoas.infrastructure.repository.pessoa.dto.SalvarPessoaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {
        PessoaFisicaMapper.class,
        PessoaJuridicaMapper.class
    }
)
public interface PessoaMapper {
  PessoaDTO toDTO(IPessoa pessoa);

  SalvarPessoa toSalvarPessoa(SalvarPessoaDTO salvarPessoaDTO);
}
