# Parrot Tech

## Integrantes do Grupo

### Julia Mariano Barsotti Ferreira (RM552713)  
**Responsável pelo desenvolvimento Java,**  
**Mobile Application Development e Compliance, Quality Assurance & Tests.**

### Leonardo Gaspar Saheb (RM553383)  
**Responsável pela configuração e gerenciamento do banco de dados,**  
**pelo desenvolvimento de Disruptive Architectures: IoT, IoB & Generative AI e Compliance, Quality Assurance & Tests.**

### Caio Eduardo Nascimento Martins (RM554025)
**Responsável pelo desenvolvimento em Advanced Business Development with .NET,**  
**DevOps Tools & Cloud Computing e Compliance, Quality Assurance & Tests.**

  ##

## Pré-requisitos
Para rodar este projeto, você precisará ter os seguintes itens instalados em sua máquina:

- **JDK (Java Development Kit)**: Certifique-se de ter o JDK instalado, pois é necessário para compilar e rodar aplicações Java.
- **Java 17+**: A aplicação foi desenvolvida usando a versão 17 ou superior do Java.
- **Maven**: O Maven é utilizado para gerenciar as dependências e compilar o projeto.

  ##
  
# Instruções para Rodar a Aplicação

### 1. Clonar o Repositório
Primeiro, clone o repositório do projeto para sua máquina local. No terminal, execute:

```bash
git clone https://github.com/seu-usuario/odontoprev-project.git
```

Acesse o diretório do projeto clonado:
```
cd odontoprev-project
```
Compile o projeto usando o Maven, pulando os testes. Execute o seguinte comando:
```
mvn clean package -Dmaven.test.skip=true
```
Após a compilação, o Maven irá gerar um arquivo JAR no diretório target. O caminho do arquivo JAR será:
```
<seu_diretorio_do_projeto>\target\<nome_do_projeto>-<versao>.jar
```
E então é so rodar sua Application!

  ##
  
## Imagem dos Diagramas

(Imagem dos diagramas aqui.)

## Vídeo de Apresentação

  ##

# Documentação da API

##Usuários
- **GET** `/api/usuarios/listar`: Lista todos os usuários.
- **POST** `/api/usuarios/cadastrar`: Cria um novo usuário.
- **GET** `/api/usuarios/{id}`: Retorna os detalhes de um id (do usuario) específico.
- **PUT** `/api/usuarios/{id}`: Atualiza as informações de um usuário.
- **DELETE** `/api/usuarios/{id}`: Remove um usuário.

## Previsões
- **GET** `/api/previsao-usuario`: Lista todas as previsões de usuários.
- **POST** `/api/previsao-usuario`: Cria uma nova previsão de usuário.
- **GET** `/api/previsao-usuario/{id}`: Retorna os detalhes de uma previsão específica.
- **PUT** `/api/previsao-usuario/{id}`: Atualiza uma previsão de usuário.
- **DELETE** `/api/previsao-usuario/{id}`: Remove uma previsão de usuário.

---

## Atendimentos
- **GET** `/api/atendimento-usuario`: Lista todos os atendimentos de usuários.
- **POST** `/api/atendimento-usuario`: Cria um novo atendimento de usuário.
- **GET** `/api/atendimento-usuario/{id}`: Retorna os detalhes de um atendimento específico.
- **PUT** `/api/atendimento-usuario/{id}`: Atualiza um atendimento de usuário.
- **DELETE** `/api/atendimento-usuario/{id}`: Remove um atendimento de usuário.

---

## Imagens
- **GET** `/api/imagem-usuario`: Lista todas as imagens de usuários.
- **POST** `/api/imagem-usuario`: Adiciona uma nova imagem de usuário.
- **GET** `/api/imagem-usuario/{id}`: Retorna os detalhes de uma imagem específica.
- **PUT** `/api/imagem-usuario/{id}`: Atualiza as informações de uma imagem de usuário.
- **DELETE** `/api/imagem-usuario/{id}`: Remove uma imagem de usuário.

---

## Endereços
- **GET** `/api/endereco-usuario`: Lista todos os endereços de usuários.
- **POST** `/api/endereco-usuario`: Adiciona um novo endereço de usuário.
- **GET** `/api/endereco-usuario/{id}`: Retorna os detalhes de um endereço específico.
- **PUT** `/api/endereco-usuario/{id}`: Atualiza as informações de um endereço de usuário.
- **DELETE** `/api/endereco-usuario/{id}`: Remove um endereço de usuário.

---

## Contatos
- **GET** `/api/contato-usuario`: Lista todos os contatos de usuários.
- **POST** `/api/contato-usuario`: Adiciona um novo contato de usuário.
- **GET** `/api/contato-usuario/{id}`: Retorna os detalhes de um contato específico.
- **PUT** `/api/contato-usuario/{id}`: Atualiza as informações de um contato de usuário.
- **DELETE** `/api/contato-usuario/{id}`: Remove um contato de usuário.


