package com.example.cadastropessoas.infrastructure.controller.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.endereco.exception.EnderecoNaoEncontradoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.entity.SalvarPessoa;
import com.example.cadastropessoas.domain.pessoa.exception.PessoaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoafisica.exception.PessoaFisicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoajuridica.exception.PessoaJuridicaNaoEncontradaException;
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

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public PessoaDTO criarPessoa(
      @RequestBody SalvarPessoaDTO salvarPessoaDTO
  ) throws TelefoneNaoEncontradoException,
      PessoaFisicaNaoEncontradaException,
      EnderecoNaoEncontradoException,
      PessoaNaoEncontradaException,
      InputInvalidoException,
      PessoaJuridicaNaoEncontradaException
  {
    return salvar(salvarPessoaDTO);
  }

  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public PessoaDTO atualizarPessoa(
      @PathVariable Long id,
      @RequestBody SalvarPessoaDTO salvarPessoaDTO
  ) throws TelefoneNaoEncontradoException,
      PessoaFisicaNaoEncontradaException,
      EnderecoNaoEncontradoException,
      PessoaNaoEncontradaException,
      InputInvalidoException,
      PessoaJuridicaNaoEncontradaException
  {
    salvarPessoaDTO.setId(id);

    return salvar(salvarPessoaDTO);
  }

  private PessoaDTO salvar(@RequestBody SalvarPessoaDTO salvarPessoaDTO)
      throws InputInvalidoException,
      TelefoneNaoEncontradoException,
      EnderecoNaoEncontradoException,
      PessoaNaoEncontradaException,
      PessoaFisicaNaoEncontradaException,
      PessoaJuridicaNaoEncontradaException
  {
    SalvarPessoa dadosPessoa = pessoaMapper.toSalvarPessoa(salvarPessoaDTO);

    var input = new SalvarPessoaUseCase.Input();
    input.setId(dadosPessoa.getId());
    input.setNome(dadosPessoa.getNome());
    input.setEmail(dadosPessoa.getEmail());
    input.setPessoaFisica(dadosPessoa.getPessoaFisica());
    input.setPessoaJuridica(dadosPessoa.getPessoaJuridica());
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
