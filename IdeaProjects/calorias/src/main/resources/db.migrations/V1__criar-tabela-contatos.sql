CREATE SEQUENCE contatos_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE tbl_contatos(
    id INTEGER DEFAULT contatos_seq.NEXTVAL NOT NULL,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL
);