CREATE TABLE IF NOT EXISTS setor(
	setor_cod varchar(20),
	setor_desc varchar(255),
	CONSTRAINT pk_setor PRIMARY KEY (setor_cod)
);

CREATE TABLE IF NOT EXISTS visitantes(
	visit_cpf varchar(11) NOT NULL,
	visit_nome varchar (50) NOT NULL,
	visit_fone varchar(255),
	CONSTRAINT visitante_cpf UNIQUE (visit_cpf),
	CONSTRAINT visitante_cpf_pk PRIMARY KEY (visit_cpf)
);

CREATE TABLE IF NOT EXISTS usuarios(
	usuario_nm varchar(255) NOT NULL,
	usuario_ap varchar(255) NOT NULL,
	cpf_num varchar(11) NOT NULL,
	birth_data date NULL,
	email varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	recupera_cod varchar(255) NULL,
	ativo boolean,
	constraint pk_usua_cpf PRIMARY KEY (cpf_num),
	CONSTRAINT usua_cpf UNIQUE (cpf_num),
	CONSTRAINT usua_email UNIQUE (email)
);
COMMENT ON TABLE usuarios IS 'usuarios do sistema';

CREATE TABLE IF NOT EXISTS visita(
    visit_cpf varchar(11) NOT NULL,
    cpf_num varchar(11),
    placa varchar(10),
    dataent timestamp(8),
    datasai timestamp(8),
    setor_cod varchar(11),
    autorizado_por varchar(255),
    agendada boolean,
    CONSTRAINT fk_visit_cpf FOREIGN KEY (visit_cpf) REFERENCES visitantes(visit_cpf),
    CONSTRAINT fk_cpf_num FOREIGN KEY (cpf_num) REFERENCES usuarios(cpf_num),
    CONSTRAINT fk_setor_cod FOREIGN KEY (setor_cod) REFERENCES setor(setor_cod)
		
);

CREATE TABLE IF NOT EXISTS autorizacoes(
	autoriza_cod varchar(100) NOT NULL,
	autoriza_desc varchar(255) NOT NULL,
	CONSTRAINT autori_cod PRIMARY KEY (autoriza_cod)
);
COMMENT ON TABLE autorizacoes IS 'códigos de autorização do sistema';

CREATE TABLE IF NOT EXISTS permissoes(
	cpf_num varchar(11) NOT NULL,
	autoriza_cod varchar(100) NOT NULL,
	CONSTRAINT permi_id UNIQUE (cpf_num, autoriza_cod), 
	CONSTRAINT permi_autori_cod FOREIGN KEY (autoriza_cod) REFERENCES autorizacoes(autoriza_cod),
	CONSTRAINT permi_usua_cpf FOREIGN KEY (cpf_num) REFERENCES usuarios(cpf_num)
);
COMMENT ON TABLE permissoes IS 'permissões de acesso dos usuários';