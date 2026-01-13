import type {PessoaFisica} from "@/model/pessoa-fisica/PessoaFisica.ts";
import type {Endereco} from "@/model/endereco/Endereco.ts";
import type {Telefone} from "@/model/telefone/Telefone.ts";
import type {PessoaJuridica} from "@/model/pessoa-juridica/PessoaJuridica.ts";

export interface Pessoa {
  id?: number
  nome: string
  email: string
  pessoaFisica?: PessoaFisica
  pessoaJuridica?: PessoaJuridica
  enderecos: Endereco[]
  telefones: Telefone[]
}
