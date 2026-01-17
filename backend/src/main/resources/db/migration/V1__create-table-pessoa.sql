CREATE TABLE pessoa (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE pessoa_fisica (
  id BIGSERIAL PRIMARY KEY,
  id_pessoa BIGINT NOT NULL UNIQUE,
  cpf VARCHAR(11) NOT NULL UNIQUE,
  data_nascimento DATE NOT NULL,
  FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

CREATE TABLE pessoa_juridica (
  id BIGSERIAL PRIMARY KEY,
  id_pessoa BIGINT NOT NULL UNIQUE,
  cnpj VARCHAR(14) NOT NULL UNIQUE,
  razao_social VARCHAR(255) NOT NULL,
  FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

CREATE TABLE endereco (
  id BIGSERIAL PRIMARY KEY,
  id_pessoa BIGINT NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numero VARCHAR(20) NOT NULL,
  bairro VARCHAR(100) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  uf VARCHAR(2) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  complemento VARCHAR(255),
  FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

CREATE TABLE telefone (
  id BIGSERIAL PRIMARY KEY,
  id_pessoa BIGINT NOT NULL,
  numero VARCHAR(20) NOT NULL,
  tipo_telefone VARCHAR(50) NOT NULL,
  FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);
