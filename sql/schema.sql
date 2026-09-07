CREATE TABLE professor (
    matricula VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE materia (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    horas INT NOT NULL
);

CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    periodo INT NOT NULL,
    qtd_alunos INT NOT NULL,
    matricula_professor VARCHAR(20) REFERENCES professor(matricula),
    id_materia INT REFERENCES materia(id)
);
