# monopolioweb

Projeto do modulo **Criando um projeto monolitico para web** da EBAC.

## O que foi pedido

O modulo mostra a criacao de uma aplicacao web monolitica Java usando:

- Maven para gerenciar o projeto e gerar um arquivo `war`;
- JSF para a interface web;
- JPA para persistencia;
- PostgreSQL como banco de dados;
- WildFly como servidor de aplicacao.

O exercicio pede que o projeto seja criado seguindo essa estrutura e versionado no GitHub.

## Estrutura criada

- `src/main/java/br/com/ebac/monopolioweb/domain`: entidade JPA `Cliente`.
- `src/main/java/br/com/ebac/monopolioweb/dao`: DAO com operacoes de CRUD.
- `src/main/java/br/com/ebac/monopolioweb/service`: regra de negocio e validacoes simples.
- `src/main/java/br/com/ebac/monopolioweb/controller`: managed bean JSF.
- `src/main/webapp`: paginas XHTML, template e recursos CSS.
- `src/main/resources/META-INF/persistence.xml`: unidade JPA apontando para datasource PostgreSQL do WildFly.

## Banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE monopolioweb;
```

No WildFly, cadastre um datasource JNDI com o nome:

```text
java:/jdbc/MonopolioWebDS
```

Exemplo de configuracao via CLI do WildFly:

```bash
module add --name=org.postgresql --resources=/caminho/postgresql-42.7.7.jar --dependencies=jakarta.api,java.transaction.xa

/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver)

data-source add --name=MonopolioWebDS --jndi-name=java:/jdbc/MonopolioWebDS --driver-name=postgresql --connection-url=jdbc:postgresql://localhost:5432/monopolioweb --user-name=postgres --password=postgres
```

## Como executar

Com Maven instalado:

```bash
mvn clean package
```

Depois publique `target/monopolioweb.war` no WildFly e acesse:

```text
http://localhost:8080/monopolioweb
```

## Conferencia do exercicio

O projeto atende aos pontos do modulo:

- aplicacao monolitica em um unico `war`;
- interface web JSF;
- persistencia via JPA;
- uso de PostgreSQL por datasource no servidor de aplicacao;
- deploy esperado em WildFly;
- CRUD completo de clientes.
