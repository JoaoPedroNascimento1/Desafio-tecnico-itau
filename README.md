# 💸 Desafio Técnico Itaú — API de Transações e Estatísticas

> API REST desenvolvida em Java + Spring Boot para o desafio técnico do Itaú Unibanco.
> Recebe transações financeiras e retorna estatísticas em tempo real sobre os últimos 60 segundos.

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/Lombok-1.18-pink?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Armazenamento-Em_Memória-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge" />
</p>

---

## 📌 Sobre o Desafio

Este projeto é a solução para o **Desafio Técnico de Desenvolvedor Júnior do Itaú Unibanco**, cujo objetivo é avaliar:

- Qualidade e organização do código
- Compreensão de regras de negócio
- Capacidade de estruturar uma API REST
- Escrita de testes automatizados
- Preocupação com segurança e boas práticas

A missão é construir uma **API REST** que:
1. Receba e valide transações financeiras
2. Permita limpar o histórico de transações
3. Retorne estatísticas calculadas sobre as transações dos **últimos 60 segundos**

> **Restrição importante:** Nenhum banco de dados é utilizado — todos os dados são armazenados **em memória**.

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.5 | Framework web |
| Spring Web MVC | 4.0.5 | Camada HTTP / Controllers |
| Spring Validation | 4.0.5 | Validação de entradas (Bean Validation) |
| Lombok | latest | Redução de boilerplate (getters, construtores) |
| Maven | 3.9+ | Gerenciamento de dependências e build |
| JUnit 5 | (via Spring Test) | Testes unitários e de integração |
| MockMvc | (via Spring Test) | Testes de camada web |

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas inspirada no padrão **MVC**, com separação clara de responsabilidades:

```
br.com.desafioItau
├── controller/          # Camada de entrada HTTP — recebe e responde requisições REST
│   ├── TransacaoController.java
│   └── EstatisticaController.java
│
├── service/             # Camada de negócio — validações, regras e cálculos
│   ├── TransacaoService.java
│   └── EstatisticaService.java
│
├── repository/          # Camada de persistência em memória (List / estrutura thread-safe)
│   └── TransacaoRepository.java
│
├── dto/                 # Objetos de transferência de dados (Request e Response)
│   ├── TransacaoRequest.java
│   └── EstatisticaResponse.java
│
└── DesafioItauApplication.java   # Classe principal — ponto de entrada da aplicação
```

**Fluxo de uma requisição:**

```
Cliente HTTP
    │
    ▼
Controller  ──►  Service  ──►  Repository (memória)
    │               │
    ▼               ▼
Response       Regras de negócio + Cálculo de estatísticas
```

> Dados armazenados em memória com estrutura thread-safe para evitar condições de corrida em chamadas concorrentes.

---

## ✅ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 17** (ou superior compatível com o Spring Boot 4.x)
- **Maven 3.9+** — ou use o Maven Wrapper incluso (`./mvnw`)
- **Git** (para clonar o repositório)

Verifique as versões instaladas:

```bash
java -version
mvn -version
```

---

## 🚀 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/JoaoPedroNascimento1/Desafio-tecnico-itau.git
cd Desafio-tecnico-itau
```

### 2. Build do projeto

**Linux/macOS:**
```bash
./mvnw clean install
```

**Windows:**
```bash
mvnw.cmd clean install
```

### 3. Execute a aplicação

**Via Maven Wrapper (recomendado para desenvolvimento):**
```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

**Via JAR gerado:**
```bash
java -jar target/desafioItau-0.0.1-SNAPSHOT.jar
```

### 4. Verifique se está rodando

A aplicação sobe na porta **8080** por padrão:

```bash
curl http://localhost:8080/estatistica
# Esperado: {"count":0,"sum":0.0,"avg":0.0,"min":0.0,"max":0.0}
```

---

## ⚙️ Variáveis de Ambiente / Configurações

O projeto não requer variáveis de ambiente externas para execução básica. Configurações ficam em `src/main/resources/application.properties` (ou `application.yml`).

| Configuração | Valor Padrão | Descrição |
|---|---|---|
| `server.port` | `8080` | Porta da aplicação |
| `spring.application.name` | `desafioItau` | Nome da aplicação |

> Para alterar a porta, adicione em `application.properties`:
> ```properties
> server.port=8081
> ```

---

## 📡 Endpoints da API

A API expõe três endpoints, todos trafegando **JSON**.

---

### `POST /transacao`
Registra uma nova transação financeira.

**Request body:**
```json
{
  "valor": 123.45,
  "dataHora": "2024-08-07T12:34:56.789-03:00"
}
```

| Campo | Tipo | Obrigatório | Descrição |
|---|---|---|---|
| `valor` | `Double` | ✅ Sim | Valor decimal da transação |
| `dataHora` | `OffsetDateTime` (ISO 8601) | ✅ Sim | Data e hora da transação |

**Respostas:**

| Código | Descrição |
|---|---|
| `201 Created` | Transação aceita e registrada com sucesso |
| `422 Unprocessable Entity` | Transação inválida (valor negativo, data futura, etc.) |
| `400 Bad Request` | JSON malformado ou campos ausentes |

**Exemplo com `curl`:**
```bash
curl -X POST http://localhost:8080/transacao \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 150.00,
    "dataHora": "2024-08-07T10:00:00.000-03:00"
  }'
```

---

### `DELETE /transacao`
Remove **todas** as transações armazenadas em memória.

**Sem request body.**

| Código | Descrição |
|---|---|
| `200 OK` | Todas as transações foram apagadas com sucesso |

**Exemplo com `curl`:**
```bash
curl -X DELETE http://localhost:8080/transacao
```

---

### `GET /estatistica`
Retorna estatísticas das transações ocorridas nos **últimos 60 segundos**.

**Response body:**
```json
{
  "count": 3,
  "sum": 350.75,
  "avg": 116.92,
  "min": 50.25,
  "max": 200.00
}
```

| Campo | Tipo | Descrição |
|---|---|---|
| `count` | `Long` | Quantidade de transações no período |
| `sum` | `Double` | Soma total dos valores |
| `avg` | `Double` | Média dos valores |
| `min` | `Double` | Menor valor registrado |
| `max` | `Double` | Maior valor registrado |

> Quando não há transações nos últimos 60 segundos, todos os campos retornam `0`.

| Código | Descrição |
|---|---|
| `200 OK` | Estatísticas calculadas com sucesso |

**Exemplo com `curl`:**
```bash
curl http://localhost:8080/estatistica
```

---

## 📐 Regras de Negócio

Uma transação só é aceita (`201 Created`) se **todas** as condições abaixo forem atendidas:

1. **`valor` e `dataHora` são obrigatórios** — campos nulos ou ausentes retornam `400 Bad Request`
2. **Valor não pode ser negativo** — `valor < 0` retorna `422 Unprocessable Entity`
3. **Valor deve ser maior ou igual a zero** — `valor >= 0.0` é aceito
4. **Transação não pode ser do futuro** — `dataHora` posterior ao momento atual retorna `422 Unprocessable Entity`
5. **Transação pode ser de qualquer momento no passado** — não há limite inferior de data

Para o cálculo de estatísticas:
- Apenas transações com `dataHora` dentro dos **últimos 60 segundos** são consideradas
- Transações mais antigas que 60 segundos são **ignoradas automaticamente** no cálculo
- A janela de tempo é calculada dinamicamente a cada requisição `GET /estatistica`

---

## 🧪 Testes

O projeto utiliza **JUnit 5** com **MockMvc** para testes da camada web.

### Executar todos os testes

```bash
# Linux/macOS
./mvnw test

# Windows
mvnw.cmd test
```

### Build sem executar testes

```bash
./mvnw clean install -DskipTests
```

### Relatórios de teste

Após execução, os relatórios ficam disponíveis em:
```
target/surefire-reports/
```

**Cenários testados:**

| Cenário | Endpoint | Status Esperado |
|---|---|---|
| Transação válida | `POST /transacao` | `201` |
| Transação com valor negativo | `POST /transacao` | `422` |
| Transação com data futura | `POST /transacao` | `422` |
| JSON malformado | `POST /transacao` | `400` |
| Campos obrigatórios ausentes | `POST /transacao` | `400` |
| Deletar transações | `DELETE /transacao` | `200` |
| Estatísticas sem transações | `GET /estatistica` | `200` com zeros |
| Estatísticas com transações | `GET /estatistica` | `200` com dados calculados |

---

## 🔍 Solução de Problemas

**Porta 8080 já em uso:**
```bash
# Verifique o processo ocupando a porta
lsof -i :8080

# Ou altere a porta no application.properties
server.port=8081
```

**Permissão negada ao executar `./mvnw`:**
```bash
chmod +x mvnw
```

**Versão do Java incompatível:**
```bash
# Verifique a versão instalada
java -version
# O projeto requer Java 17
```

---

## 👤 Autor

**João Pedro Nascimento**

[![GitHub](https://img.shields.io/badge/GitHub-JoaoPedroNascimento1-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/JoaoPedroNascimento1)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-joaopedronascimento1-0A66C2?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/joaopedronascimento1/)

---

## 📄 Licença

Este projeto foi desenvolvido como solução para um desafio técnico. Uso livre para fins educacionais.

---

<p align="center">
  Feito com ☕ e Java para o desafio técnico do <strong>Itaú Unibanco</strong>
</p>
