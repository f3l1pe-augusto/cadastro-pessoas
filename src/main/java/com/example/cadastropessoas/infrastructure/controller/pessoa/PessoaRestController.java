package com.example.cadastropessoas.infrastructure.controller.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.endereco.exception.EnderecoNaoEncontradoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.entity.SalvarPessoa;
import com.example.cadastropessoas.domain.pessoa.exception.PessoaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoafisica.exception.PessoaFisicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoafisica.gateway.PessoaFisicaGateway;
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.pessoajuridica.exception.PessoaJuridicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoajuridica.gateway.PessoaJuridicaGateway;
import com.example.cadastropessoas.domain.telefone.exception.TelefoneNaoEncontradoException;
import com.example.cadastropessoas.infrastructure.mapper.pessoa.PessoaMapper;
import com.example.cadastropessoas.infrastructure.repository.pessoa.dto.PessoaDTO;
import com.example.cadastropessoas.infrastructure.repository.pessoa.dto.SalvarPessoaDTO;
import com.example.cadastropessoas.usecase.pessoa.SalvarPessoaUseCase;
import com.example.cadastropessoas.usecase.pessoa.ObterPessoaPorIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaRestController {

  private final SalvarPessoaUseCase salvarPessoaUseCase;
  private final ObterPessoaPorIdUseCase obterPessoaPorIdUseCase;
  private final PessoaMapper pessoaMapper;
  private final PessoaFisicaGateway pessoaFisicaGateway;
  private final PessoaJuridicaGateway pessoaJuridicaGateway;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public PessoaDTO salvarPessoa(@RequestBody SalvarPessoaDTO salvarPessoaDTO)
      throws PessoaFisicaNaoEncontradaException,
      PessoaJuridicaNaoEncontradaException,
      InputInvalidoException,
      TelefoneNaoEncontradoException,
      EnderecoNaoEncontradoException,
      PessoaNaoEncontradaException
  {
    SalvarPessoa dadosPessoa = pessoaMapper.toSalvarPessoa(salvarPessoaDTO);

    IPessoaFisica pessoaFisica = pessoaFisicaGateway.obterPorId(dadosPessoa.getIdPessoaFisica());

    IPessoaJuridica pessoaJuridica = pessoaJuridicaGateway.obterPorId(dadosPessoa.getIdPessoaJuridica());

    var input = new SalvarPessoaUseCase.Input();
    input.setId(dadosPessoa.getId());
    input.setNome(dadosPessoa.getNome());
    input.setEmail(dadosPessoa.getEmail());
    input.setPessoaFisica(pessoaFisica);
    input.setPessoaJuridica(pessoaJuridica);
    input.setTelefones(dadosPessoa.getTelefones());
    input.setEnderecos(dadosPessoa.getEnderecos());

    SalvarPessoaUseCase.Output output = salvarPessoaUseCase.executar(input);
    IPessoa pessoa = output.getPessoa();

    return pessoaMapper.toDTO(pessoa);
  }

  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public PessoaDTO obterPessoaPorId(@PathVariable Long id)
      throws PessoaNaoEncontradaException,
      InputInvalidoException
  {
    var input = new ObterPessoaPorIdUseCase.Input();
    input.setId(id);

    ObterPessoaPorIdUseCase.Output output = obterPessoaPorIdUseCase.executar(input);
    IPessoa pessoa = output.getPessoa();

    return pessoaMapper.toDTO(pessoa);
  }
}
