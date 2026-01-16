export interface Endereco {
  id?: number;
  logradouro: string;
  numero: string;
  bairro: string;
  cidade: string;
  uf: 'AC' | 'AL' | 'AP' | 'AM' | 'BA' | 'CE' | 'DF' | 'ES' | 'GO' | 'MA' | 'MT' | 'MS' | 'MG' |
    'PA' | 'PB' | 'PR' | 'PE' | 'PI' | 'RJ' | 'RN' | 'RS' | 'RO' | 'RR' | 'SC' | 'SP' | 'SE' | 'TO';
  cep: string;
  complemento?: string;
}
