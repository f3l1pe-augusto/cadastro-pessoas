import type { Pessoa } from '@/model/pessoa/Pessoa.ts'

const BASE_URL = 'http://localhost:8080/cadastro-pessoas/api/pessoa'

export async function listarPessoas(): Promise<Pessoa[]> {
  const response = await fetch(BASE_URL)
  return response.json()
}

export async function obterPessoa(id: number): Promise<Pessoa> {
  const response = await fetch(`${BASE_URL}/${id}`)
  return response.json()
}

export async function criarPessoa(pessoa: Pessoa): Promise<Pessoa> {
  const response = await fetch(BASE_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(pessoa)
  })
  return response.json()
}

export async function atualizarPessoa(id: number, pessoa: Pessoa): Promise<Pessoa> {
  const response = await fetch(`${BASE_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(pessoa)
  })
  return response.json()
}

export async function deletarPessoa(id: number): Promise<void> {
  await fetch(`${BASE_URL}/${id}`, { method: 'DELETE' })
}
