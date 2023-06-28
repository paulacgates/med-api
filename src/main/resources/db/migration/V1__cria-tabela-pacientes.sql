CREATE TABLE pacientes (
    id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    cpf VARCHAR2(11) NOT NULL UNIQUE,
    logradouro VARCHAR2(100) NOT NULL,
    bairro VARCHAR2(100) NOT NULL,
    cep VARCHAR2(9) NOT NULL,
    complemento VARCHAR2(100),
    numero VARCHAR2(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR2(100) NOT NULL,

    CONSTRAINT pacientes_pk PRIMARY KEY (id)
);