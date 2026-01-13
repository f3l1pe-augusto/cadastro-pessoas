package com.example.cadastropessoas.usecase.pessoa;

import com.example.cadastropessoas.domain.core.exception.InputInvalidoException;
import com.example.cadastropessoas.domain.pessoa.entity.IPessoa;
import com.example.cadastropessoas.domain.pessoa.gateway.PessoaValidador;
import com.example.cadastropessoas.domain.pessoafisica.entity.IPessoaFisica;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaValidador implements PessoaValidador {

    @Override
    public boolean aceita(IPessoa pessoa) {
        return pessoa instanceof IPessoaFisica;
    }

    @Override
    public void validar(IPessoa pessoa) throws InputInvalidoException {
      IPessoaFisica pessoaFisica = (IPessoaFisica) pessoa;

        if (pessoaFisica.getCpf() == null || pessoaFisica.getCpf().isBlank()) {
            throw new InputInvalidoException("CPF inválido.");
        }

        if (pessoaFisica.getDataNascimento() == null) {
            throw new InputInvalidoException("Data de nascimento inválida.");
        }
    }
}
