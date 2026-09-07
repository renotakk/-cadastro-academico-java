# Sistema de Cadastro Acadêmico (Java + PostgreSQL)

Aplicação de console em Java para cadastro de professores, matérias e turmas, com persistência em banco de dados PostgreSQL via JDBC.

## Funcionalidades

- Cadastro de professores (matrícula e nome)
- Cadastro de matérias (nome e carga horária)
- Cadastro de turmas, vinculando período, quantidade de alunos, professor e matéria

## Estrutura do banco

O banco tem três tabelas, criadas pelo script [`sql/schema.sql`](sql/schema.sql):

- `professor` (matricula, nome)
- `materia` (id, nome, horas)
- `turma` (id, periodo, qtd_alunos, matricula_professor, id_materia)

## Como rodar

1. Tenha o PostgreSQL instalado e crie um banco (ex: `aula05`).
2. Rode o script `sql/schema.sql` nesse banco para criar as tabelas.
3. Copie `config.properties.example` para `config.properties` e preencha com os dados do seu banco:
   ```
   cp config.properties.example config.properties
   ```
4. Compile e execute:
   ```
   javac -cp .:postgresql.jar src/aula05.java -d out
   java -cp out:.:postgresql.jar aula05
   ```
   (é necessário ter o driver JDBC do PostgreSQL — `postgresql.jar` — no classpath)

## Tecnologias

- Java
- JDBC
- PostgreSQL

## Status

Em desenvolvimento — projeto de estudo aplicado à prática de bancos de dados.
