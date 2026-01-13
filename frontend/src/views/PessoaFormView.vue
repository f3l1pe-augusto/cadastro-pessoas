<template>
  <div class="container mt-4">
    <h2>{{ isEdicao ? 'Editar Pessoa' : 'Cadastrar Pessoa' }}</h2>

    <PessoaForm
      :pessoa="pessoa"
      @salvar="salvar"
    />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import PessoaForm from '@/components/PessoaForm.vue'
import { obterPessoa, criarPessoa, atualizarPessoa } from '@/api/pessoa.api'
import type {Pessoa} from "@/model/pessoa/Pessoa.ts";

const route = useRoute()
const router = useRouter()

const pessoa = ref<Pessoa>({
  nome: '',
  email: '',
  enderecos: [],
  telefones: []
})

const isEdicao = !!route.params.id

onMounted(async () => {
  if (isEdicao) {
    pessoa.value = await obterPessoa(Number(route.params.id))
  }
})

async function salvar(dados: Pessoa) {
  if (isEdicao && dados.id) {
    await atualizarPessoa(dados.id, dados)
  } else {
    await criarPessoa(dados)
  }

  await router.push('/pessoas')
}
</script>
