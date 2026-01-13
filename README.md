# Cadastro de Pessoas

Desafio técnico Nexum Tecnologia: Sistema de CRUD para cadastro de pessoas físicas e jurídicas - Por Felipe Ferreira.

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture**, com separação clara de responsabilidades:

```
src/main/java/com/example/cadastropessoas/
├── domain/                    # Entidades de domínio
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

## 🚀 Tecnologias utilizadas

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

## 🔧 Execução e configurações

### Pré-requisitos
- Java 23 ou superior
- Maven 3.6+

### Como executar

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

### Banco de dados

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

### Endpoints disponíveis

#### Criar pessoa física
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

#### Criar pessoa jurídica
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

#### Atualizar pessoa
```http
PUT /pessoa/{id}
Content-Type: application/json

{
  "nome": "João Silva Atualizado",
  "email": "joao.silva.novo@email.com",
  ...
}
```

#### Buscar pessoa por ID
```http
GET /pessoa/{id}
```

#### Listar todas as pessoas
```http
GET /pessoa
```

#### Deletar pessoa
```http
DELETE /pessoa/{id}
```
