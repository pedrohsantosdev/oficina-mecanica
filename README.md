# 🚗 Sistema de Oficina Mecânica

Sistema de gerenciamento de oficina mecânica desenvolvido em Java utilizando JDBC e MySQL.

O projeto foi criado com o objetivo de praticar conceitos de Programação Orientada a Objetos, persistência de dados com JDBC, padrão DAO e organização em camadas.

## 📚 Tecnologias

- Java
- JDBC
- MySQL
- IntelliJ IDEA
- Git e GitHub

## 📁 Estrutura do Projeto

```
src
├── application
├── db
├── model
│   ├── dao
│   ├── dao.impl
│   ├── entities
│   ├── enums
│   ├── services
```

## 🛠 Funcionalidades

### Cliente

- Cadastrar cliente
- Atualizar cliente
- Buscar cliente por ID
- Listar clientes
- Excluir cliente

### Veículo

- Cadastrar veículo
- Atualizar veículo
- Buscar veículo por ID
- Buscar veículo por placa
- Listar veículos
- Excluir veículo

### Ordem de Serviço

- Abrir ordem de serviço
- Atualizar ordem de serviço
- Buscar ordem por ID
- Buscar ordens por cliente
- Buscar ordens por veículo
- Buscar ordens por status
- Listar ordens de serviço
- Excluir ordem de serviço

## 📌 Regras de Negócio

### Cliente

- Não permite cadastro de clientes nulos.
- Apenas clientes existentes podem ser atualizados ou removidos.

### Veículo

- Todo veículo pertence a um cliente.
- Busca por placa.
- Não permite atualizar ou excluir veículos inexistentes.

### Ordem de Serviço

- Toda ordem deve estar vinculada a um veículo.
- A data de saída não pode ser anterior à data de entrada.
- Não são aceitas datas futuras.
- O valor da ordem deve ser maior que zero.
- Controle de status da ordem utilizando Enum.

## 📊 Modelo Relacional

```
Cliente
--------
id
nome
cpf
telefone
email

        │ 1
        │
        │
        ▼ N

Veiculo
--------
id
cliente_id
placa
marca
modelo
ano
cor
quilometragem

        │ 1
        │
        │
        ▼ N

OrdemServico
------------
id
veiculo_id
data_entrada
data_saida
problema
diagnostico
valor
status_ordem
```

## 🏗 Arquitetura

O projeto foi desenvolvido utilizando arquitetura em camadas:

- Application
- Service
- DAO
- JDBC
- Banco de Dados

Essa organização facilita manutenção, reutilização de código e separação de responsabilidades.

## 💻 Conceitos Praticados

- Programação Orientada a Objetos
- Encapsulamento
- Associação entre objetos
- Enum
- Exceptions
- JDBC
- PreparedStatement
- ResultSet
- DAO Pattern
- Factory Pattern
- CRUD
- Relacionamentos 1:N
- Validação de regras de negócio

## 🚀 Objetivo

Este projeto foi desenvolvido para consolidar conhecimentos em Java e JDBC através da implementação de um sistema de gerenciamento de oficina mecânica utilizando boas práticas de programação e arquitetura em camadas.
