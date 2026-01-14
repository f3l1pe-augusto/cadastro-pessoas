<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h2>Lista de pessoas</h2>

      <router-link to="/cadastrar" class="btn btn-success">
        Cadastrar pessoa
      </router-link>
    </div>

    <table class="table table-striped table-bordered table-responsive">
      <thead class="table-light">
      <tr>
        <th>Nome</th>
        <th>CPF / CNPJ</th>
        <th style="width: 160px" class="text-center">Ações</th>
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
            class="btn btn-sm btn-primary me-2"
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

    <div
      class="modal fade"
      id="modalExclusao"
      tabindex="-1"
      aria-labelledby="modalExclusaoLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalExclusaoLabel">
              Confirmar Exclusão
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            Deseja realmente excluir <strong>{{ pessoaSelecionada?.nome }}</strong>?
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Cancelar
            </button>
            <button
              type="button"
              class="btn btn-danger"
              @click="excluir"
            >
              Excluir
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { listarPessoas, deletarPessoa } from '@/api/pessoa.api'
import type {Pessoa} from "@/model/pessoa/Pessoa.ts"
import { Modal } from 'bootstrap'

const pessoas = ref<Pessoa[]>([])
const pessoaSelecionada = ref<Pessoa | null>(null)
let modalExclusao: Modal | null = null

async function carregar() {
  pessoas.value = await listarPessoas()
}

function confirmarExclusao(pessoa: Pessoa) {
  pessoaSelecionada.value = pessoa

  if (!modalExclusao) {
    const modalElement = document.getElementById('modalExclusao')
    if (modalElement) {
      modalExclusao = new Modal(modalElement)
    }
  }

  modalExclusao?.show()
}

async function excluir() {
  if (!pessoaSelecionada.value?.id) return

  await deletarPessoa(pessoaSelecionada.value.id)
  modalExclusao?.hide()
  pessoaSelecionada.value = null
  await carregar()
}

onMounted(carregar)
</script>
