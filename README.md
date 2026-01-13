# Cadastro de Pessoas

Sistema de CRUD para cadastro de pessoas físicas e jurídicas desenvolvido com Spring Boot, seguindo os princípios de Clean Architecture.

## 📋 Sobre o Projeto

Este projeto implementa um sistema completo para gerenciamento de cadastro de pessoas, contemplando tanto pessoas físicas quanto jurídicas, com suporte a múltiplos endereços e telefones por pessoa.

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**, com separação clara de responsabilidades:

```
src/main/java/com/example/cadastropessoas/
├── domain/                    # Regras de negócio e entidades de domínio
│   ├── core/                  # Exceções e componentes compartilhados
│   ├── pessoa/                # Entidade Pessoa
│   ├── pessoafisica/          # Entidade Pessoa Física
│   ├── pessoajuridica/        # Entidade Pessoa Jurídica
│   ├── endereco/              # Entidade Endereço
│   ├── telefone/              # Entidade Telefone
│   └── tipotelefone/          # Entidade Tipo de Telefone
├── usecase/                   # Casos de uso da aplicação
│   └── pessoa/
│       ├── SalvarPessoaUseCase.java
│       ├── ObterPessoaPorIdUseCase.java
│       ├── ListarPessoasUseCase.java
│       ├── DeletarPessoaUseCase.java
│       ├── PessoaFisicaValidador.java
│       └── PessoaJuridicaValidador.java
└── infrastructure/            # Implementações de infraestrutura
    ├── controller/            # Controladores REST
    ├── repository/            # Repositórios JPA
    ├── gateway/               # Implementações de gateways
    └── mapper/                # Mapeadores (MapStruct)
```

## 🚀 Tecnologias Utilizadas

- **Java 23**
- **Spring Boot 4.0.1**
  - Spring Web
  - Spring Data JPA
  - Spring DevTools
- **H2 Database** - Banco de dados em arquivo
- **Lombok** - Redução de boilerplate
- **MapStruct 1.6.3** - Mapeamento de objetos
- **Maven** - Gerenciamento de dependências

## 📦 Funcionalidades

### Pessoa
- ✅ Criar pessoa (física ou jurídica)
- ✅ Atualizar pessoa
- ✅ Buscar pessoa por ID
- ✅ Listar todas as pessoas
- ✅ Deletar pessoa

## 🔧 Configuração e Execução

### Pré-requisitos
- Java 23 ou superior
- Maven 3.6+

### Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/f3l1pe-augusto/cadastro-pessoas.git
cd cadastro-pessoas
```

2. Execute o projeto:
```bash
./mvnw spring-boot:run
```

Ou no Windows:
```bash
mvnw.cmd spring-boot:run
```

3. A aplicação estará disponível em:
```
http://localhost:8080/cadastro-pessoas/api
```

### Banco de Dados

O projeto utiliza H2 Database em modo arquivo. Os dados são persistidos em:
```
./data/cadastro_pessoas_db.mv.db
```

**Configurações do banco:**
- URL: `jdbc:h2:file:./data/cadastro_pessoas_db`
- Usuário: `sa`
- Senha: `password`
- DDL: `create-drop` (recriar estrutura a cada execução)

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/cadastro-pessoas/api/pessoa
```

### Endpoints Disponíveis

#### Criar Pessoa Física
```http
POST /pessoa
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "pessoaFisica": {
    "cpf": "12345678901",
    "dataNascimento": "1990-05-15"
  },
  "enderecos": [
    {
      "logradouro": "Rua das Flores",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01234567",
      "complemento": "Apto 45"
    }
  ],
  "telefones": [
    {
      "numero": "11987654321",
      "tipoTelefone": "Telefone Celular"
    }
  ]
}
```

#### Criar Pessoa Jurídica
```http
POST /pessoa
Content-Type: application/json

{
  "nome": "XYZ Comércio",
  "email": "contato@empresa.com",
  "pessoaJuridica": {
    "cnpj": "12345678000190",
    "razaoSocial": "Empresa XYZ Ltda"
  },
  "enderecos": [
    {
      "logradouro": "Av. Paulista",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01310100"
    }
  ],
  "telefones": [
    {
      "numero": "1133334444",
      "tipoTelefone": "Telefone Fixo"
    }
  ]
}
```

#### Atualizar Pessoa
```http
PUT /pessoa/{id}
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "email": "joao.silva.novo@email.com",
  ...
}
```

#### Buscar Pessoa por ID
```http
GET /pessoa/{id}
```

#### Listar Todas as Pessoas
```http
GET /pessoa
```

#### Deletar Pessoa
```http
DELETE /pessoa/{id}
```

## 📝 Estrutura de Dados

### Pessoa
- `id`: Long
- `nome`: String
- `email`: String
- `pessoaFisica`: PessoaFisica (opcional)
- `pessoaJuridica`: PessoaJuridica (opcional)
- `enderecos`: Collection<Endereco>
- `telefones`: Collection<Telefone>

### Pessoa Física
- `cpf`: String
- `dataNascimento`: LocalDate

### Pessoa Jurídica
- `cnpj`: String
- `razaoSocial`: String

### Endereço
- `logradouro`: String
- `cidade`: String
- `estado`: String
- `cep`: String
- `complemento`: String

### Telefone
- `numero`: String
- `tipoTelefone`: TipoTelefone (enum)
