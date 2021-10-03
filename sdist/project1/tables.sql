CREATE TABLE centro (
    nomecentro text,
    localizacao text,
    PRIMARY KEY (nomecentro)
);

CREATE TABLE inscricao (
    id SERIAL,
    nome text,
    genero text,
    idade integer,
    nomecentro text,
    PRIMARY KEY (id, nome),
    CONSTRAINT fk_centro FOREIGN KEY (nomecentro) REFERENCES centro(nomecentro)
);

CREATE TABLE vacinados (
    id SERIAL,
    nome text,
    genero text,
    idade text,
    data date,
    vacina text,
    efeitos boolean,
    centro text,
    PRIMARY KEY (id, nome)
);

CREATE TABLE vacinas (
    nomevacina text,
    PRIMARY KEY (nomevacina)
);