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
import com.example.cadastropessoas.domain.pessoajuridica.entity.IPessoaJuridica;
import com.example.cadastropessoas.domain.telefone.entity.ITelefone;
import com.example.cadastropessoas.domain.telefone.entity.SalvarTelefone;
import com.example.cadastropessoas.domain.telefone.exception.TelefoneNaoEncontradoException;
import com.example.cadastropessoas.domain.telefone.gateway.TelefoneGateway;
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
  private List<PessoaValidador> validadores;

  public Output executar(Input input)
      throws PessoaNaoEncontradaException,
      InputInvalidoException,
      EnderecoNaoEncontradoException, TelefoneNaoEncontradoException {
    IPessoa pessoa = pessoaGateway.obterPorId(input.getId());

    validar(input, pessoa);

    pessoa.setNome(input.getNome());
    pessoa.setEmail(input.getEmail());
    pessoa.applyPessoaFisica(input.getPessoaFisica());
    pessoa.applyPessoaJuridica(input.getPessoaJuridica());

    var enderecos = new ArrayList<IEndereco>();

    for (SalvarEndereco enderecoInput : input.getEnderecos()) {
      enderecos.add(enderecoGateway.obterPorId(enderecoInput.getId()));
    }

    pessoa.applyEnderecos(enderecos);

    var telefones = new ArrayList<ITelefone>();

    for (SalvarTelefone telefoneInput : input.getTelefones()) {
      telefones.add(telefoneGateway.obterPorId(telefoneInput.getId()));
    }

    pessoa.applyTelefones(telefones);

    IPessoa pessoaSalva = pessoaGateway.salvar(pessoa);

    Output output = new Output();
    output.setPessoa(pessoaSalva);

    return output;
  }

  private void validar(Input input, IPessoa pessoa) throws InputInvalidoException {
    if (input == null) {
      throw new InputInvalidoException("Input inválido.");
    }

    if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
      throw new InputInvalidoException("Nome inválido.");
    }

    if (pessoa.getEmail() == null || pessoa.getEmail().isBlank()) {
      throw new InputInvalidoException("E-mail inválido.");
    }

    // Validações específicas por tipo de pessoa
    for (PessoaValidador validador : validadores) {
      if (validador.aceita(pessoa)) {
        validador.validar(pessoa);
      }
    }

    if (pessoa.getTelefones() == null || pessoa.getTelefones().isEmpty()) {
      throw new InputInvalidoException("A pessoa deve possuir ao menos um telefone.");
    }

    if (pessoa.getEnderecos() == null || pessoa.getEnderecos().isEmpty()) {
      throw new InputInvalidoException("A pessoa deve possuir ao menos um endereço.");
    }
  }

  @Getter
  @Setter
  public static class Input {
    private Long id;
    private String nome;
    private String email;
    private IPessoaFisica pessoaFisica;
    private IPessoaJuridica pessoaJuridica;
    private List<SalvarEndereco> enderecos;
    private List<SalvarTelefone> telefones;
  }

  @Getter
  @Setter
  public static class Output {
    private IPessoa pessoa;
  }
}
