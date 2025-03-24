create table aluno (
	id serial primary key,
	nome text not null,
	idade integer not null,
	cpf text not null,
	email text not null,
	senha text not null,
	matricula text not null,
	curso text not null
);
create table livros (
    id SERIAL PRIMARY KEY,
    titulo varchar(200) not null,
    autor varchar(100) not null,
    ano_edicao int not null,
	quantidade_disponivel int not null,
	corredor char not null,
	prateleira char not null
);

create table funcionarios (
	id serial primary key,
	nome text not null,
	idade integer not null,
	cpf text not null,
	email text not null,
	senha text not null,
	administrador boolean not null
);

create table emprestimo (
	id serial primary key,
	data_emprestimo date not null,
	data_devolucao date not null,
	matricula text not null,
	titulo text not null
);

alter table emprestimo
add foreign key (matricula) references aluno,
add foreign key (titulo) references livros;
