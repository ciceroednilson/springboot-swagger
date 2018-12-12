CREATE DATABASE blog;


CREATE TABLE tb_pessoa
(
    id_pessoa         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ds_nome           VARCHAR(100) NOT NULL,
    fl_ativo          bit     NOT NULL
);