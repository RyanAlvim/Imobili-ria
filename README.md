# 🏢 Lopes Elite — Sistema de Gestão

> Aplicação web desenvolvida em Java para gerenciamento de usuários, negócios, pagamentos, controle financeiro e comunicação interna.

---

## 📌 Sobre o Projeto

O **Lopes Elite** é uma aplicação web desenvolvida inteiramente em **Java**, utilizando **Spring Boot**, criada para centralizar processos administrativos e financeiros de uma empresa do setor imobiliário.

O sistema reúne diferentes funcionalidades em uma única plataforma, permitindo o gerenciamento de:

- 👥 Usuários e corretores
- 🏢 Negócios imobiliários
- 💰 Comissões e pagamentos
- 📈 Ganhos
- 📉 Custos
- 📢 Mural de avisos
- 👤 Perfis de usuários
- 🔐 Autenticação
- 🤖 Automação de processos através do Selenium

Além do sistema web, o projeto também possui módulos de automação responsáveis por interação com sistemas externos, processamento de PDFs, comunicação via FTP e consultas automatizadas através do Selenium.

---

## 🎯 Objetivo

O principal objetivo do projeto é centralizar processos administrativos e financeiros em uma aplicação web.

A plataforma permite que diferentes usuários tenham acesso a funcionalidades de acordo com seu cargo, enquanto administradores possuem acesso aos recursos de gerenciamento do sistema.

---

## 🏗️ Arquitetura

A aplicação segue uma arquitetura baseada no padrão utilizado pelo Spring:

    ┌───────────────────────┐
    │       Interface       │
    │      Thymeleaf        │
    └───────────┬───────────┘
                │
                ▼
    ┌───────────────────────┐
    │      Controllers      │
    │                       │
    │  Regras de aplicação  │
    └───────────┬───────────┘
                │
                ▼
    ┌───────────────────────┐
    │      Repositories     │
    │                       │
    │     Spring Data JPA   │
    └───────────┬───────────┘
                │
                ▼
    ┌───────────────────────┐
    │        MySQL          │
    │                       │
    │      Banco de dados   │
    └───────────────────────┘

---

## ☕ Tecnologias

### Backend

- Java 8
- Spring Boot 2.7.3
- Spring MVC
- Spring Data JPA
- Spring Security

### Frontend

- Thymeleaf
- HTML
- CSS
- JavaScript
- Materialize
- Argon Dashboard

### Banco de Dados

- MySQL

### Automação e Integrações

- Selenium WebDriver
- Apache PDFBox
- Apache Commons Net
- FTP
- Unirest
- Gson
- JavaMail

### Build

- Maven

---

## 🔐 Autenticação e Segurança

O sistema utiliza **Spring Security** para proteger as páginas da aplicação.

O fluxo de autenticação utiliza o e-mail do usuário como identificador.

As senhas dos usuários são armazenadas utilizando:

    BCryptPasswordEncoder

As páginas administrativas também possuem verificações de cargo para restringir determinadas funcionalidades aos usuários autorizados.

---

## 👥 Perfis de Usuário

O sistema trabalha principalmente com três cargos:

| Cargo | Função |
|---|---|
| `ADM` | Administração e gerenciamento geral |
| `GERENTE` | Gerenciamento de equipe e informações financeiras |
| `CORRETOR` | Acompanhamento dos próprios negócios e pagamentos |

O dashboard apresentado ao usuário é adaptado de acordo com seu cargo.

---

## 📊 Dashboard

A página inicial apresenta informações diferentes dependendo do perfil.

### 👤 Corretor

O corretor pode visualizar informações relacionadas aos seus próprios negócios, incluindo:

- Negócios cadastrados
- Valores pendentes
- Valores recebidos
- Valor total

### 👔 Gerente

O gerente possui informações financeiras e também dados relacionados aos seus afiliados.

### 🛠️ Administrador

O administrador possui uma visão geral do sistema, incluindo:

- Negócios
- Valores pendentes
- Valores pagos
- Afiliados
- Informações financeiras da empresa
- Gerenciamento administrativo

---

## 🏢 Gerenciamento de Negócios

O sistema possui um módulo específico para cadastro de negócios imobiliários.

Um negócio pode armazenar informações como:

- Nome
- E-mail
- Tipo de negócio
- PV
- Referência do imóvel
- Endereço do imóvel
- Valor do negócio
- Tipo de comissão
- Gerente
- Rede
- Royalties
- Impostos
- Vistoria
- Data prevista para pagamento
- Data de assinatura
- Percentual de comissão
- Percentual de gestão
- Saldo
- Distribuidor
- Valor destinado à empresa
- Status do pagamento

---

## 🧮 Cálculo de Comissões

O projeto possui uma classe dedicada ao processamento dos valores financeiros:

    Calculo.java

O cálculo considera diferentes componentes do negócio, como:

    Valor do negócio
            ↓
    Desconto da rede
            ↓
    Custos
            ↓
    Vistoria
            ↓
    Comissão do corretor
            ↓
    Comissão do gerente
            ↓
    Resultado da empresa

A aplicação calcula os valores correspondentes ao corretor, gerente, rede, custos e empresa.

---

## 💰 Pagamentos

Os negócios possuem controle de pagamento.

Um negócio pode estar inicialmente como:

    PENDENTE

Após a realização do pagamento, o sistema atualiza o registro para:

    PAGO

O processo de pagamento também atualiza os valores financeiros associados ao usuário.

Além disso, a data do pagamento é registrada.

---

## 📧 Notificações por E-mail

O projeto possui uma classe dedicada ao envio de mensagens:

    Email.java

Ela é utilizada em diferentes partes da aplicação.

Por exemplo, quando um novo usuário é cadastrado, o sistema pode enviar uma mensagem contendo informações de acesso.

Também existe envio de notificação relacionado ao cadastro de negócios e à realização de pagamentos.

---

## 📈 Controle de Ganhos

O módulo de ganhos permite ao administrador cadastrar receitas da empresa.

Cada registro possui informações como:

- Nome
- Data
- Valor
- Tipo de negócio
- Descrição
- Mês de referência

Também é possível consultar os ganhos por mês.

---

## 📉 Controle de Custos

O módulo de custos permite registrar despesas da empresa.

Cada registro possui:

- Nome do custo
- Data
- Valor
- Mês de referência

O sistema também calcula o valor total dos custos cadastrados e permite consultas por mês.

---

## 📢 Mural de Avisos

O sistema possui um mural interno para comunicação com os usuários.

Administradores podem:

- Criar avisos
- Editar avisos
- Revogar avisos
- Excluir avisos
- Visualizar avisos

Cada publicação possui informações como:

- Título
- Tema
- Texto
- Data de publicação
- Data de revogação
- Situação

As publicações possuem estados como:

    ATIVA
    REVOGADA

---

## 👤 Gerenciamento de Usuários

Administradores podem realizar o gerenciamento dos usuários cadastrados.

Entre as operações disponíveis estão:

- Cadastro
- Listagem
- Edição
- Exclusão

Os usuários podem possuir informações como:

- Nome
- E-mail
- Telefone
- Endereço
- CEP
- PIX
- Cargo
- Gerente
- CRECI
- Banco
- Agência
- Conta corrente
- Valores pendentes
- Valores recebidos

---

## 👤 Perfil

Cada usuário possui uma página de perfil com informações cadastradas no sistema.

Entre elas:

- Nome
- E-mail
- Data de entrada
- Telefone
- Endereço
- PIX
- Gerente
- CEP

---

# 🤖 Módulo de Automação

Além do sistema de gestão, o projeto possui um conjunto de classes voltadas para automação de processos externos.

Entre elas estão:

    Login
    Buscar
    Reader
    Assertiva
    FtpClient
    Discord
    Request

Esse módulo utiliza principalmente **Selenium WebDriver** para automatizar interações com páginas web.

---

## 🌐 Automação com Selenium

A aplicação utiliza Selenium para automatizar o navegador Google Chrome.

O código configura o navegador para execução automatizada e, em determinadas rotinas, utiliza o modo:

    headless

Isso permite executar determinadas operações sem a necessidade de manter a interface gráfica do navegador aberta.

---

## 🏠 Consulta de Informações de Imóveis

O módulo de automação possui uma rotina que lê informações de imóveis a partir de um arquivo:

    cadastroImovel.txt

Cada registro é utilizado para realizar uma consulta automatizada em um sistema externo.

O fluxo é aproximadamente:

    cadastroImovel.txt
            ↓
    Selenium
            ↓
    Sistema externo
            ↓
    Consulta do imóvel
            ↓
    Geração do documento
            ↓
    NotificacaoLancamento.pdf
            ↓
    PDFBox
            ↓
    Extração das informações

---

## 📄 Processamento de PDF

A classe:

    PDF.Reader

utiliza o **Apache PDFBox** para abrir e extrair texto dos documentos PDF gerados durante o processo automatizado.

O processamento procura informações específicas no documento e armazena os dados encontrados em estruturas Java.

Após a leitura, o arquivo temporário pode ser removido pelo próprio processo.

---

## 🔎 Consulta de Dados Externos

Após a extração das informações dos documentos, o projeto possui um módulo que utiliza Selenium para consultar uma plataforma externa.

A classe:

    Assertiva.Assertiva

automatiza o preenchimento de formulários e a coleta de informações disponibilizadas pelo serviço.

Os dados obtidos são organizados e posteriormente gravados em:

    nomes.txt

O arquivo possui uma estrutura semelhante a:

    nome;telefone;endereco

---

## 📡 FTP

A classe:

    FTP.FtpClient

utiliza o **Apache Commons Net** para comunicação com um servidor FTP.

O módulo permite, entre outras operações:

- Conectar ao servidor
- Autenticar
- Alterar diretórios
- Enviar arquivos

O FTP é utilizado pelo módulo de automação como parte do fluxo de transferência de arquivos.

---

## 🔗 Integração HTTP

A classe:

    Request.Request

utiliza **Unirest** para realizar requisições HTTP para um serviço externo utilizado pelo projeto.

Esse recurso é acessível através do módulo administrativo de console.

---

## 🖥️ Console Administrativo

O sistema possui uma página de console acessível aos administradores.

O administrador pode enviar uma informação para o módulo de requisição, que realiza uma chamada HTTP para o serviço configurado.

Após a execução, o sistema pode apresentar os dados gerados no arquivo:

    nomes.txt

O console também possui uma função para encerrar os processos automatizados associados ao Selenium.

---

## 🧩 Estrutura do Projeto

A estrutura principal do código está organizada aproximadamente da seguinte maneira:

    src/
    └── main/
        ├── java/
        │   ├── API/
        │   │   └── Discord.java
        │   │
        │   ├── Assertiva/
        │   │   └── Assertiva.java
        │   │
        │   ├── Calculo/
        │   │   └── Calculo.java
        │   │
        │   ├── Consultar/
        │   │   └── Buscar.java
        │   │
        │   ├── Email/
        │   │   └── Email.java
        │   │
        │   ├── FTP/
        │   │   └── FtpClient.java
        │   │
        │   ├── Login/
        │   │   └── Login.java
        │   │
        │   ├── PDF/
        │   │   └── Reader.java
        │   │
        │   ├── Request/
        │   │   └── Request.java
        │   │
        │   └── com/br/LopesElite_Geral/
        │       └── LopesElite_Geral/
        │           ├── Controllers/
        │           ├── Entity/
        │           ├── Repository/
        │           ├── WebSecurityConfig/
        │           └── LopesEliteGeralApplication.java
        │
        └── resources/
            ├── static/
            ├── templates/
            └── application.properties

---

## 🗄️ Persistência de Dados

O sistema utiliza **Spring Data JPA** para persistência dos dados.

As principais entidades são:

- `PessoasEntity`
- `cadastroNegocios`
- `CustosEntity`
- `GanhosEntity`
- `MuralEntity`

Essas entidades representam as principais informações utilizadas pelo sistema.

---

## 🗃️ Principais Tabelas

| Entidade | Responsabilidade |
|---|---|
| `PessoasEntity` | Usuários e informações financeiras |
| `cadastroNegocios` | Negócios e comissões |
| `CustosEntity` | Custos da empresa |
| `GanhosEntity` | Ganhos da empresa |
| `MuralEntity` | Avisos e comunicados |

---

## 🔄 Fluxo Geral

O funcionamento geral do sistema pode ser representado como:

    Usuário
       │
       ▼
    Login
       │
       ▼
    Spring Security
       │
       ▼
    Dashboard
       │
       ├───────────────┐
       │               │
       ▼               ▼
    Negócios       Financeiro
       │               │
       ▼               ├── Ganhos
    Comissões           └── Custos
       │
       ▼
    Pagamentos
       │
       ▼
    Notificação

    Administração
       │
       ├── Usuários
       ├── Negócios
       ├── Pagamentos
       ├── Mural
       └── Automação

---

## 📦 Dependências Principais

O projeto utiliza Maven para gerenciamento das dependências.

Entre as principais bibliotecas utilizadas estão:

- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Web
- Thymeleaf
- MySQL Connector
- Selenium
- JavaMail
- Spring Session
- Apache Commons IO
- Apache Commons Net
- Gson
- Unirest
- Apache PDFBox

---

## 🚀 Execução

### Requisitos

Para executar o projeto são necessários, em princípio:

- Java 8
- Maven
- MySQL
- Google Chrome
- ChromeDriver compatível com o navegador
- Configuração das integrações externas utilizadas pelo projeto

---

## 🗄️ Banco de Dados

O projeto utiliza MySQL.

É necessário criar e configurar o banco utilizado pela aplicação antes da execução.

As configurações de conexão devem ser fornecidas através de variáveis de ambiente ou outro mecanismo seguro de configuração.

> **Não utilize credenciais diretamente no código-fonte ou no `application.properties` em ambientes de produção.**

---

## 🔒 Configuração de Credenciais

As credenciais de:

- Banco de dados
- Serviços externos
- FTP
- APIs
- E-mail
- Selenium

devem ser configuradas de maneira segura.

Uma abordagem recomendada é utilizar variáveis de ambiente:

    DB_USERNAME
    DB_PASSWORD
    FTP_HOST
    FTP_USERNAME
    FTP_PASSWORD
    API_TOKEN

Essas informações não devem ser versionadas no Git.

---

## ⚠️ Observações de Segurança

Este projeto contém integrações com serviços externos e automações que dependem de credenciais.

Antes de publicar ou executar uma versão deste projeto em ambiente real:

- Remova credenciais do código-fonte.
- Revogue tokens antigos.
- Altere senhas que tenham sido expostas.
- Utilize variáveis de ambiente.
- Não publique cookies, tokens ou chaves de API.
- Revise os endpoints externos utilizados pela aplicação.
- Verifique as permissões das contas utilizadas pelas automações.

---

## 📚 Conhecimentos Aplicados

Este projeto reúne diversos conceitos de desenvolvimento de software:

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Orientação a Objetos

### Banco de Dados

- MySQL
- JPA
- Repositories
- Persistência de entidades

### Web

- Thymeleaf
- HTML
- CSS
- JavaScript
- MVC

### Segurança

- Autenticação
- BCrypt
- Controle de acesso
- Sessões

### Automação

- Selenium WebDriver
- Automação de navegador
- Processamento de documentos
- PDFBox
- FTP
- HTTP

### Integrações

- E-mail
- FTP
- APIs HTTP
- Serviços externos
- Processamento de arquivos

---

## 📌 Status

> 🚧 Projeto desenvolvido anteriormente.

O projeto representa uma aplicação Java/Spring Boot voltada para **gestão empresarial, controle financeiro, gerenciamento de negócios e automação de processos externos**.

---

## 👨‍💻 Autor

**Ryan Alvim**

Desenvolvedor interessado em:

- ☕ Java
- 🌐 Desenvolvimento Web
- 🔧 Backend
- 🤖 Automação
- 🗄️ Banco de Dados
- 🔗 Integração de Sistemas

### Contato

- GitHub: [@RyanAlvim](https://github.com/RyanAlvim)
- E-mail: [ryanalvim65@gmail.com](mailto:ryanalvim65@gmail.com)

---

<p align="center">
  Desenvolvido com ☕ Java e Spring Boot
</p>
