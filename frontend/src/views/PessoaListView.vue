<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>Lista de Pessoas</h2>

      <router-link to="/cadastrar" class="btn btn-primary">
        Cadastrar Pessoa
      </router-link>
    </div>

    <table class="table table-striped table-bordered table-responsive">
      <thead class="table-light">
      <tr>
        <th>Nome</th>
        <th>CPF / CNPJ</th>
        <th style="width: 160px">Ações</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="pessoa in pessoas" :key="pessoa.id">
        <td>{{ pessoa.nome }}</td>
        <td>
          {{ pessoa.pessoaFisica?.cpf || pessoa.pessoaJuridica?.cnpj }}
        </td>
        <td class="text-center">
          <router-link
            :to="`/editar/${pessoa.id}`"
            class="btn btn-sm btn-warning me-2"
          >
            Editar
          </router-link>

          <button
            class="btn btn-sm btn-danger"
            @click="confirmarExclusao(pessoa)"
          >
            Excluir
          </button>
        </td>
      </tr>

      <tr v-if="pessoas.length === 0">
        <td colspan="3" class="text-center text-muted">
          Nenhuma pessoa cadastrada
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { listarPessoas, deletarPessoa } from '@/api/pessoa.api'
import type {Pessoa} from "@/model/pessoa/Pessoa.ts";

const pessoas = ref<Pessoa[]>([])
const pessoaSelecionada = ref<Pessoa | null>(null)

async function carregar() {
  pessoas.value = await listarPessoas()
}

function confirmarExclusao(pessoa: Pessoa) {
  pessoaSelecionada.value = pessoa

  if (confirm(`Deseja excluir ${pessoa.nome}?`)) {
    excluir()
  }
}

async function excluir() {
  if (!pessoaSelecionada.value?.id) return

  await deletarPessoa(pessoaSelecionada.value.id)
  pessoaSelecionada.value = null
  await carregar()
}

onMounted(carregar)
</script>
