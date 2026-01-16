package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.endereco.entity.IEndereco;
import com.example.cadastropessoas.domain.endereco.entity.SalvarEndereco;
import com.example.cadastropessoas.domain.endereco.exception.EnderecoNaoEncontradoException;
import com.example.cadastropessoas.domain.endereco.gateway.EnderecoGateway;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.exception.PessoaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaGateway;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaValidador;
import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import com.example.cadastropessoas.domain.pessoafisica.entity.SalvarPessoaFisica;
import com.example.cadastropessoas.domain.pessoafisica.exception.PessoaFisicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoafisica.gateway.PessoaFisicaGateway;
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.pessoajuridica.entity.SalvarPessoaJuridica;
import com.example.cadastropessoas.domain.pessoajuridica.exception.PessoaJuridicaNaoEncontradaException;
import com.example.cadastropessoas.domain.pessoajuridica.gateway.PessoaJuridicaGateway;
import com.example.cadastropessoas.domain.telefone.entity.ITelefone;
import com.example.cadastropessoas.domain.telefone.entity.SalvarTelefone;
import com.example.cadastropessoas.domain.telefone.exception.TelefoneNaoEncontradoException;
import com.example.cadastropessoas.domain.telefone.gateway.TelefoneGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SalvarPessoaUseCase {

  private final PessoaGateway pessoaGateway;
  private final EnderecoGateway enderecoGateway;
  private final TelefoneGateway telefoneGateway;
  private final PessoaFisicaGateway pessoaFisicaGateway;
  private final PessoaJuridicaGateway pessoaJuridicaGateway;
  private final List<PessoaValidador> validadores;

  @Transactional(rollbackOn = Exception.class)
  public Output executar(Input input)
      throws PessoaNaoEncontradaException,
      InputInvalidoException,
      EnderecoNaoEncontradoException,
      TelefoneNaoEncontradoException,
      PessoaFisicaNaoEncontradaException,
      PessoaJuridicaNaoEncontradaException
  {
    validarInput(input);

    IPessoa pessoa = pessoaGateway.obterPorId(input.getId());
    pessoa.setNome(input.getNome());
    pessoa.setEmail(input.getEmail());

    processarPessoaFisica(input, pessoa);

    processarPessoaJuridica(input, pessoa);

    validarTipoPessoa(pessoa);

    processarEnderecos(input, pessoa);

    processarTelefones(input, pessoa);

    IPessoa pessoaSalva = pessoaGateway.salvar(pessoa);

    Output output = new Output();
    output.setPessoa(pessoaSalva);

    return output;
  }

  private void processarPessoaFisica(Input input, IPessoa pessoa) throws PessoaFisicaNaoEncontradaException {
    if (input.getPessoaFisica() != null) {
      IPessoaFisica pessoaFisica = pessoa.getPessoaFisica() != null
          ? pessoa.getPessoaFisica()
          : pessoaFisicaGateway.obterPorId(null);

      pessoaFisica.setCpf(input.getPessoaFisica().getCpf());
      pessoaFisica.setDataNascimento(input.getPessoaFisica().getDataNascimento());
      pessoaFisica.applyPessoa(pessoa);
      pessoa.applyPessoaFisica(pessoaFisica);
    }
  }

  private void processarPessoaJuridica(Input input, IPessoa pessoa) throws PessoaJuridicaNaoEncontradaException {
    if (input.getPessoaJuridica() != null) {
      IPessoaJuridica pessoaJuridica = pessoa.getPessoaJuridica() != null
          ? pessoa.getPessoaJuridica()
          : pessoaJuridicaGateway.obterPorId(null);

      pessoaJuridica.setCnpj(input.getPessoaJuridica().getCnpj());
      pessoaJuridica.setRazaoSocial(input.getPessoaJuridica().getRazaoSocial());
      pessoaJuridica.applyPessoa(pessoa);
      pessoa.applyPessoaJuridica(pessoaJuridica);
    }
  }

  private void processarEnderecos(Input input, IPessoa pessoa) throws EnderecoNaoEncontradoException {
    List<IEndereco> enderecosNovos = new ArrayList<>();

    for (SalvarEndereco enderecoInput : input.getEnderecos()) {
      IEndereco endereco = enderecoInput.getId() != null
          ? enderecoGateway.obterPorId(enderecoInput.getId())
          : enderecoGateway.obterPorId(null);

      endereco.setLogradouro(enderecoInput.getLogradouro());
      endereco.setNumero(enderecoInput.getNumero());
      endereco.setBairro(enderecoInput.getBairro());
      endereco.setCidade(enderecoInput.getCidade());
      endereco.setUf(enderecoInput.getUf());
      endereco.setCep(enderecoInput.getCep());
      endereco.setComplemento(enderecoInput.getComplemento());
      endereco.applyPessoa(pessoa);

      enderecosNovos.add(endereco);
    }

    pessoa.applyEnderecos(enderecosNovos);
  }

  private void processarTelefones(Input input, IPessoa pessoa) throws TelefoneNaoEncontradoException {
    List<ITelefone> telefonesNovos = new ArrayList<>();

    for (SalvarTelefone telefoneInput : input.getTelefones()) {
      ITelefone telefone = telefoneInput.getId() != null
          ? telefoneGateway.obterPorId(telefoneInput.getId())
          : telefoneGateway.obterPorId(null);

      telefone.setNumero(telefoneInput.getNumero());
      telefone.setTipoTelefone(telefoneInput.getTipoTelefone());
      telefone.applyPessoa(pessoa);

      telefonesNovos.add(telefone);
    }

    pessoa.applyTelefones(telefonesNovos);
  }

  private void validarInput(Input input) throws InputInvalidoException, PessoaNaoEncontradaException {
    if (input == null) {
      throw new InputInvalidoException("Input inválido.");
    }

    if (input.getNome() == null || input.getNome().isBlank()) {
      throw new InputInvalidoException("Nome inválido.");
    }

    if (input.getEmail() == null || input.getEmail().isBlank()) {
      throw new InputInvalidoException("E-mail inválido.");
    }

    validarEmailUnico(input);
    validarEndereco(input);
    validarTelefone(input);
  }

  private void validarEmailUnico(Input input) throws InputInvalidoException {
    if (pessoaGateway.emailExisteParaOutraPessoa(input.getEmail(), input.getId())) {
      throw new InputInvalidoException("E-mail já cadastrado.");
    }
  }

  private void validarEndereco(Input input) throws InputInvalidoException {
    if (input.getEnderecos() == null || input.getEnderecos().isEmpty()) {
      throw new InputInvalidoException("A pessoa deve possuir ao menos um endereço.");
    }

    if (input.getEnderecos().stream().anyMatch(endereco -> endereco.getLogradouro() == null || endereco.getLogradouro().isBlank())) {
      throw new InputInvalidoException("Logradouro do endereço inválido.");
    }

    if (input.getEnderecos().stream().anyMatch(endereco -> endereco.getNumero() == null || endereco.getNumero().isBlank())) {
      throw new InputInvalidoException("Número do endereço inválido.");
    }

    if (input.getEnderecos().stream().anyMatch(endereco -> endereco.getBairro() == null || endereco.getBairro().isBlank())) {
      throw new InputInvalidoException("Bairro do endereço inválido.");
    }

    if (input.getEnderecos().stream().anyMatch(endereco -> endereco.getCidade() == null || endereco.getCidade().isBlank())) {
      throw new InputInvalidoException("Cidade do endereço inválida.");
    }

    if (input.getEnderecos().stream().anyMatch(endereco -> endereco.getUf() == null)) {
      throw new InputInvalidoException("UF do endereço inválida.");
    }

    if (input.getEnderecos().stream().anyMatch(endereco -> endereco.getCep() == null || endereco.getCep().isBlank())) {
      throw new InputInvalidoException("CEP do endereço inválido.");
    }
  }

  private void validarTelefone(Input input) throws InputInvalidoException {
    if (input.getTelefones() == null || input.getTelefones().isEmpty()) {
      throw new InputInvalidoException("A pessoa deve possuir ao menos um telefone.");
    }

    if (input.getTelefones().stream().anyMatch(telefone -> telefone.getNumero() == null || telefone.getNumero().isBlank())) {
      throw new InputInvalidoException("Número do telefone inválido.");
    }

    if (input.getTelefones().stream().anyMatch(telefone -> telefone.getTipoTelefone() == null)) {
      throw new InputInvalidoException("Tipo do telefone inválido.");
    }
  }

  private void validarTipoPessoa(IPessoa pessoa) throws InputInvalidoException {
    for (PessoaValidador validador : validadores) {
      if (validador.aceita(pessoa)) {
        validador.validar(pessoa);
      }
    }
  }

  @Getter
  @Setter
  public static class Input {
    private Long id;
    private String nome;
    private String email;
    private SalvarPessoaFisica pessoaFisica;
    private SalvarPessoaJuridica pessoaJuridica;
    private List<SalvarEndereco> enderecos;
    private List<SalvarTelefone> telefones;
  }

  @Getter
  @Setter
  public static class Output {
    private IPessoa pessoa;
  }
}
