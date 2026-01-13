<template>
  <form @submit.prevent="submit">
    <div class="mb-3">
      <label for="nome" class="form-label">Nome</label>
      <input
        id="nome"
        v-model="form.nome"
        class="form-control"
        required
      />
    </div>

    <div class="mb-3">
      <label for="email" class="form-label">E-mail</label>
      <input
        id="email"
        v-model="form.email"
        type="email"
        class="form-control"
      />
    </div>

    <div class="mb-3">
      <label for="telefone" class="form-label">Telefone</label>
      <input
        id="telefone"
        v-model="telefonePrincipal.numero"
        class="form-control"
        required
      />
    </div>

    <div class="mb-3">
      <label for="tipoTelefone" class="form-label">Tipo do telefone</label>
      <select
        id="tipoTelefone"
        v-model="telefonePrincipal.tipoTelefone"
        class="form-select"
      >
        <option value="Telefone Celular">Celular</option>
        <option value="Telefone Residencial">Residencial</option>
        <option value="Telefone Comercial">Comercial</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="logradouro" class="form-label">Logradouro</label>
      <input
        id="logradouro"
        v-model="enderecoPrincipal.logradouro"
        class="form-control"
        required
      />
    </div>

    <div class="mb-3">
      <label for="cidade" class="form-label">Cidade</label>
      <input
        id="cidade"
        v-model="enderecoPrincipal.cidade"
        class="form-control"
        required
      />
    </div>

    <div class="mb-3">
      <label for="estado" class="form-label">Estado</label>
      <input
        id="estado"
        v-model="enderecoPrincipal.estado"
        class="form-control"
        required
      />
    </div>

    <div class="mb-3">
      <label for="cep" class="form-label">CEP</label>
      <input
        id="cep"
        v-model="enderecoPrincipal.cep"
        class="form-control"
        required
      />
    </div>

    <div class="mb-3">
      <label for="complemento" class="form-label">Complemento</label>
      <input
        id="complemento"
        v-model="enderecoPrincipal.complemento"
        class="form-control"
      />
    </div>

    <div class="mb-3">
      <label for="tipoPessoa" class="form-label">Tipo de Pessoa</label>
      <select
        id="tipoPessoa"
        v-model="tipoPessoa"
        class="form-select"
      >
        <option value="FISICA">Pessoa Física</option>
        <option value="JURIDICA">Pessoa Jurídica</option>
      </select>
    </div>

    <div v-if="tipoPessoa === 'FISICA' && form.pessoaFisica" class="mb-3">
      <label for="cpf" class="form-label">CPF</label>
      <input
        id="cpf"
        v-model="form.pessoaFisica.cpf"
        class="form-control"
      />
    </div>

    <div v-if="tipoPessoa === 'FISICA'" class="mb-3">
      <label for="dataNascimento" class="form-label">Data de Nascimento</label>
      <input
        id="dataNascimento"
        type="date"
        v-model="form.pessoaFisica!.dataNascimento"
        class="form-control"
        required
      />
    </div>

    <div v-if="tipoPessoa === 'JURIDICA' && form.pessoaJuridica" class="mb-3">
      <label for="cnpj" class="form-label">CNPJ</label>
      <input
        id="cnpj"
        v-model="form.pessoaJuridica.cnpj"
        class="form-control"
      />
    </div>

    <div v-if="tipoPessoa === 'JURIDICA'" class="mb-3">
      <label for="razaoSocial" class="form-label">Razão Social</label>
      <input
        id="razaoSocial"
        v-model="form.pessoaJuridica!.razaoSocial"
        class="form-control"
        required
      />
    </div>

    <button class="btn btn-success">
      Salvar
    </button>

    <router-link to="/pessoas" class="btn btn-secondary ms-2">
      Cancelar
    </router-link>
  </form>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import type {Pessoa} from "@/model/pessoa/Pessoa.ts";
import type {Telefone} from "@/model/telefone/Telefone.ts";
import type {Endereco} from "@/model/endereco/Endereco.ts";

const props = defineProps<{
  pessoa: Pessoa
}>()

const emit = defineEmits(['salvar'])

const form = reactive<Pessoa>({
  id: undefined,
  nome: '',
  email: '',
  enderecos: [
    {
      logradouro: '',
      cidade: '',
      estado: '',
      cep: '',
      complemento: ''
    }
  ],

  telefones: [
    {
      numero: '',
      tipoTelefone: 'Telefone Celular'
    }
  ],
  pessoaFisica: {
    cpf: '',
    dataNascimento: ''
  },
  pessoaJuridica: {
    cnpj: '',
    razaoSocial: ''
  }
})

const telefonePrincipal = ref<Telefone>({
  numero: '',
  tipoTelefone: 'Telefone Celular'
})

watch(
  telefonePrincipal,
  (novoTelefone) => {
    form.telefones = [novoTelefone]
  },
  { immediate: true, deep: true }
)

const enderecoPrincipal = ref<Endereco>({
  logradouro: '',
  cidade: '',
  estado: '',
  cep: '',
  complemento: ''
})

watch(
  enderecoPrincipal,
  (novoEndereco) => {
    form.enderecos = [novoEndereco]
  },
  { immediate: true, deep: true }
)

const tipoPessoa = ref<'FISICA' | 'JURIDICA'>('FISICA')

watch(
  () => props.pessoa,
  (novaPessoa) => {
    Object.assign(form, novaPessoa)

    if (novaPessoa.pessoaFisica) {
      tipoPessoa.value = 'FISICA'
      form.pessoaJuridica = undefined
    } else if (novaPessoa.pessoaJuridica) {
      tipoPessoa.value = 'JURIDICA'
      form.pessoaFisica = undefined
    }
  },
  { immediate: true }
)

watch(tipoPessoa, (novoTipo) => {
  if (novoTipo === 'FISICA') {
    form.pessoaFisica = {
      cpf: '',
      dataNascimento: ''
    }
    form.pessoaJuridica = undefined
  } else {
    form.pessoaJuridica = {
      cnpj: '',
      razaoSocial: ''
    }
    form.pessoaFisica = undefined
  }
})

function submit() {
  emit('salvar', { ...form })
}
</script>
