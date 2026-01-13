import { createRouter, createWebHistory } from 'vue-router'

import PessoaListView from '@/views/PessoaListView.vue'
import PessoaFormView from "@/views/PessoaFormView.vue";

const routes = [
  {
    path: '/',
    redirect: '/pessoas'
  },
  {
    path: '/pessoas',
    name: 'pessoas',
    component: PessoaListView
  },
  {
    path: '/cadastrar',
    name: 'cadastrar-pessoa',
    component: PessoaFormView
  },
  {
    path: '/editar/:id',
    name: 'editar-pessoa',
    component: PessoaFormView,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
