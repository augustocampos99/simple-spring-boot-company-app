create table companies (
	id uuid not null,
	cnpj varchar (14) not null,
	name varchar(100) not null,
	description text,
	status varchar(10) not null,
	created_at timestamp not null,
	updated_at timestamp not null,
	constraint companies_pk primary key (id),
	constraint companies_key_cnpj unique (cnpj),
	constraint check_status check (status in ('ACTIVE','INACTIVE'))
);

create table employees (
	id uuid not null,
	cpf varchar(11) not null,
	name varchar(100) not null,
	id_role uuid not null,
	id_company uuid not null,
	status varchar(10) not null,
	created_at timestamp not null,
	updated_at timestamp not null,
	constraint employees_pk primary key (id),
	constraint employees_key_cpf unique (cpf),
	constraint check_status check (status in ('ACTIVE','INACTIVE'))
);

create table roles (
	id uuid not null,
	name varchar(100) not null,
	created_at timestamp not null,
	updated_at timestamp not null,
	constraint roles_pk primary key (id)
);