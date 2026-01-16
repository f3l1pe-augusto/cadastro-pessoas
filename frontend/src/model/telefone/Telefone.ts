export interface Telefone {
  id?: number;
  numero: string;
  tipoTelefone: 'TELEFONE_RESIDENCIAL' | 'TELEFONE_COMERCIAL' | 'TELEFONE_CELULAR';
}
