create database newM;

CREATE TABLE public.cliente (
	cod_cliente int8 not null unique,
	nome varchar(100) not null,
	nascimento timestamp without time zone not null,
	cpf int8 not null,
	celular int8 not null,
	email varchar(50) not null,
	endereco varchar(250) not null,
	observacao varchar(300),
	CONSTRAINT pk_cliente PRIMARY KEY (cod_cliente)	
);

create sequence public.cliente_seq;
