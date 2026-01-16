<template>
  <form @submit.prevent="submit">
    <div class="row">
      <div class="col-md-8 mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input
          id="nome"
          v-model="form.nome"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-4 mb-3">
        <label for="tipoPessoa" class="form-label">Tipo de pessoa</label>
        <select
          id="tipoPessoa"
          v-model="tipoPessoa"
          class="form-select"
          :disabled="isEdicao"
        >
          <option value="FISICA">Pessoa física</option>
          <option value="JURIDICA">Pessoa jurídica</option>
        </select>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input
          id="email"
          v-model="form.email"
          type="email"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-4 mb-3">
        <label for="telefone" class="form-label">Telefone</label>
        <input
          id="telefone"
          v-model="telefonePrincipal.numero"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-2 mb-3">
        <label for="tipoTelefone" class="form-label">Tipo</label>
        <select
          id="tipoTelefone"
          v-model="telefonePrincipal.tipoTelefone"
          class="form-select"
        >
          <option value="TELEFONE_CELULAR">Celular</option>
          <option value="TELEFONE_RESIDENCIAL">Residencial</option>
          <option value="TELEFONE_COMERCIAL">Comercial</option>
        </select>
      </div>
    </div>

    <div v-if="tipoPessoa === 'FISICA'" class="row">
      <div class="col-md-6 mb-3">
        <label for="cpf" class="form-label">CPF</label>
        <input
          id="cpf"
          v-model="form.pessoaFisica!.cpf"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6 mb-3">
        <label for="dataNascimento" class="form-label">Data de nascimento</label>
        <input
          id="dataNascimento"
          type="date"
          v-model="form.pessoaFisica!.dataNascimento"
          class="form-control"
          required
        />
      </div>
    </div>

    <div v-if="tipoPessoa === 'JURIDICA'" class="row">
      <div class="col-md-6 mb-3">
        <label for="cnpj" class="form-label">CNPJ</label>
        <input
          id="cnpj"
          v-model="form.pessoaJuridica!.cnpj"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-6 mb-3">
        <label for="razaoSocial" class="form-label">Razão social</label>
        <input
          id="razaoSocial"
          v-model="form.pessoaJuridica!.razaoSocial"
          class="form-control"
          required
        />
      </div>
    </div>

    <h5 class="mt-4 mb-3">Endereço</h5>

    <div class="row">
      <div class="col-md-8 mb-3">
        <label for="logradouro" class="form-label">Logradouro</label>
        <input
          id="logradouro"
          v-model="enderecoPrincipal.logradouro"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-4 mb-3">
        <label for="numero" class="form-label">Número</label>
        <input
          id="numero"
          v-model="enderecoPrincipal.numero"
          class="form-control"
          required
        />
      </div>
    </div>

    <div class="row">
      <div class="col-md-5 mb-3">
        <label for="bairro" class="form-label">Bairro</label>
        <input
          id="bairro"
          v-model="enderecoPrincipal.bairro"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-4 mb-3">
        <label for="complemento" class="form-label">Complemento</label>
        <input
          id="complemento"
          v-model="enderecoPrincipal.complemento"
          class="form-control"
        />
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="cidade" class="form-label">Cidade</label>
        <input
          id="cidade"
          v-model="enderecoPrincipal.cidade"
          class="form-control"
          required
        />
      </div>

      <div class="col-md-2 mb-3">
        <label for="uf" class="form-label">UF</label>
        <select
          id="uf"
          v-model="enderecoPrincipal.uf"
          class="form-select"
        >
          <option value="AC">AC</option>
          <option value="AL">AL</option>
          <option value="AP">AP</option>
          <option value="AM">AM</option>
          <option value="BA">BA</option>
          <option value="CE">CE</option>
          <option value="DF">DF</option>
          <option value="ES">ES</option>
          <option value="GO">GO</option>
          <option value="MA">MA</option>
          <option value="MT">MT</option>
          <option value="MS">MS</option>
          <option value="MG">MG</option>
          <option value="PA">PA</option>
          <option value="PB">PB</option>
          <option value="PR">PR</option>
          <option value="PE">PE</option>
          <option value="PI">PI</option>
          <option value="RJ">RJ</option>
          <option value="RN">RN</option>
          <option value="RS">RS</option>
          <option value="RO">RO</option>
          <option value="RR">RR</option>
          <option value="SC">SC</option>
          <option value="SP">SP</option>
          <option value="SE">SE</option>
          <option value="TO">TO</option>
        </select>
      </div>

      <div class="col-md-3 mb-3">
        <label for="cep" class="form-label">CEP</label>
        <input
          id="cep"
          v-model="enderecoPrincipal.cep"
          class="form-control"
          required
        />
      </div>
    </div>

    <div class="d-flex justify-content-end gap-2 mt-4">
      <router-link to="/pessoas" class="btn btn-secondary btn-lg px-4">
        Cancelar
      </router-link>
      <button class="btn btn-success btn-lg px-4">
        Salvar
      </button>
    </div>
  </form>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import type {Pessoa} from "@/model/pessoa/Pessoa.ts";
import type {Telefone} from "@/model/telefone/Telefone.ts";
import type {Endereco} from "@/model/endereco/Endereco.ts";

const props = defineProps<{
  pessoa?: Pessoa
}>()

const emit = defineEmits(['salvar'])

const form = reactive<Pessoa>({
  id: undefined,
  nome: '',
  email: '',
  enderecos: [],
  telefones: [],
  pessoaFisica: {
    cpf: '',
    dataNascimento: ''
  },
  pessoaJuridica: undefined
})


const tipoPessoa = ref<'FISICA' | 'JURIDICA'>('FISICA')

const telefonePrincipal = ref<Telefone>({
  numero: '',
  tipoTelefone: 'TELEFONE_CELULAR'
})

const enderecoPrincipal = ref<Endereco>({
  logradouro: '',
  numero: '',
  bairro: '',
  cidade: '',
  uf: 'AC',
  cep: '',
  complemento: ''
})

function normalizarPessoaJuridica(
  pj: Partial<Pessoa['pessoaJuridica']> | undefined
) {
  return {
    id: pj?.id,
    cnpj: pj?.cnpj ?? '',
    razaoSocial: pj?.razaoSocial ?? ''
  }
}

function normalizarTelefone(telefone: Partial<Telefone> | undefined): Telefone {
  return {
    id: telefone?.id,
    numero: telefone?.numero ?? '',
    tipoTelefone: telefone?.tipoTelefone ?? 'TELEFONE_CELULAR'
  }
}

function normalizarEndereco(endereco: Partial<Endereco> | undefined): Endereco {
  return {
    id: endereco?.id,
    logradouro: endereco?.logradouro ?? '',
    numero: endereco?.numero ?? '',
    bairro: endereco?.bairro ?? '',
    cidade: endereco?.cidade ?? '',
    uf: endereco?.uf ?? 'AC',
    cep: endereco?.cep ?? '',
    complemento: endereco?.complemento ?? ''
  }
}

const isEdicao = ref(false)

watch(
  () => props.pessoa,
  (novaPessoa) => {
    if (!novaPessoa || !novaPessoa.id) return

    isEdicao.value = true

    Object.assign(form, novaPessoa)

    if (novaPessoa.pessoaFisica) {
      tipoPessoa.value = 'FISICA'
      form.pessoaJuridica = undefined
    }

    if (novaPessoa.pessoaJuridica) {
      tipoPessoa.value = 'JURIDICA'
      form.pessoaFisica = undefined
      form.pessoaJuridica = normalizarPessoaJuridica(novaPessoa.pessoaJuridica)
    }

    if (novaPessoa.telefones?.length) {
      telefonePrincipal.value = normalizarTelefone(novaPessoa.telefones[0])
    }

    if (novaPessoa.enderecos?.length) {
      enderecoPrincipal.value = normalizarEndereco(novaPessoa.enderecos[0])
    }
  },
  { immediate: true }
)

watch(
  telefonePrincipal,
  (novoTelefone) => {
    form.telefones = [novoTelefone]
  },
  { immediate: true, deep: true }
)

watch(
  enderecoPrincipal,
  (novoEndereco) => {
    form.enderecos = [novoEndereco]
  },
  { immediate: true, deep: true }
)

watch(tipoPessoa, (novoTipo) => {
  if (isEdicao.value) return

  if (novoTipo === 'JURIDICA') {
    form.pessoaJuridica = normalizarPessoaJuridica(undefined)
    form.pessoaFisica = undefined
  }

  if (novoTipo === 'FISICA') {
    form.pessoaFisica = {
      cpf: '',
      dataNascimento: ''
    }
    form.pessoaJuridica = undefined
  }
})

function submit() {
  emit('salvar', { ...form })
}
</script>
