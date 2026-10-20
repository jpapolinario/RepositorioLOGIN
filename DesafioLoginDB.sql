
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(256) NOT NULL,
    data_nascimento VARCHAR(10) NOT NULL,
    idade INT,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    sexo VARCHAR(20),
    estado_civil VARCHAR(50),
    conjuge VARCHAR(256),
    endereco VARCHAR(256),
    cep VARCHAR(9),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    complemento VARCHAR(256),
    email VARCHAR(256) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE tipo_pessoa (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(256) NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE tipo_action_figure (
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(200) NOT NULL UNIQUE,
	excluido BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE action_figure (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(243) NOT NULL,
    tipo_action_figure_id INT NOT NULL,
    excluido BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT fk_tipo_action_figure 
        FOREIGN KEY (tipo_action_figure_id) 
        REFERENCES tipo_action_figure(id)
);

SELECT *
FROM   action_figure;
