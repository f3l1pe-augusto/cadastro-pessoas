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
  private List<PessoaValidador> validadores;

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

    if (input.getPessoaFisica() != null) {
      IPessoaFisica pessoaFisica = pessoaFisicaGateway.obterPorId(input.getPessoaFisica().getId());

      pessoaFisica.setCpf(input.getPessoaFisica().getCpf());
      pessoaFisica.setDataNascimento(input.getPessoaFisica().getDataNascimento());
      pessoaFisica.applyPessoa(pessoa);
      pessoa.applyPessoaFisica(pessoaFisica);
    }

    if (input.getPessoaJuridica() != null) {
      IPessoaJuridica pessoaJuridica = pessoaJuridicaGateway.obterPorId(input.getPessoaJuridica().getId());

      pessoaJuridica.setCnpj(input.getPessoaJuridica().getCnpj());
      pessoaJuridica.setRazaoSocial(input.getPessoaJuridica().getRazaoSocial());
      pessoaJuridica.applyPessoa(pessoa);
      pessoa.applyPessoaJuridica(pessoaJuridica);
    }

    validarPessoa(pessoa);

    var enderecos = new ArrayList<IEndereco>();
    for (SalvarEndereco enderecoInput : input.getEnderecos()) {
      IEndereco endereco = enderecoGateway.obterPorId(enderecoInput.getId());

      endereco.setLogradouro(enderecoInput.getLogradouro());
      endereco.setCidade(enderecoInput.getCidade());
      endereco.setEstado(enderecoInput.getEstado());
      endereco.setCep(enderecoInput.getCep());
      endereco.setComplemento(enderecoInput.getComplemento());
      endereco.applyPessoa(pessoa);

      enderecos.add(endereco);
    }
    pessoa.applyEnderecos(enderecos);

    var telefones = new ArrayList<ITelefone>();
    for (SalvarTelefone telefoneInput : input.getTelefones()) {
      ITelefone telefone = telefoneGateway.obterPorId(telefoneInput.getId());

      telefone.setNumero(telefoneInput.getNumero());
      telefone.setTipoTelefone(telefoneInput.getTipoTelefone());
      telefone.applyPessoa(pessoa);

      telefones.add(telefone);
    }
    pessoa.applyTelefones(telefones);

    IPessoa pessoaSalva = pessoaGateway.salvar(pessoa);

    Output output = new Output();
    output.setPessoa(pessoaSalva);
    return output;
  }

  private void validarInput(Input input) throws InputInvalidoException {
    if (input == null) {
      throw new InputInvalidoException("Input inválido.");
    }

    if (input.getNome() == null || input.getNome().isBlank()) {
      throw new InputInvalidoException("Nome inválido.");
    }

    if (input.getEmail() == null || input.getEmail().isBlank()) {
      throw new InputInvalidoException("E-mail inválido.");
    }

    if (input.getTelefones() == null || input.getTelefones().isEmpty()) {
      throw new InputInvalidoException("A pessoa deve possuir ao menos um telefone.");
    }

    if (input.getEnderecos() == null || input.getEnderecos().isEmpty()) {
      throw new InputInvalidoException("A pessoa deve possuir ao menos um endereço.");
    }
  }

  private void validarPessoa(IPessoa pessoa) throws InputInvalidoException {
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
