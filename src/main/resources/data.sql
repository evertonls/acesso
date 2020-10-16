INSERT INTO autorizacoes (autoriza_cod, autoriza_desc) VALUES
	('ROLE_ADMIN', 'ADMINISTRADOR DO SISTEMA'), ('ROLE_SUPORTE', 'ACESSO SUPORTE'),
	('ROLE_USUARIO', 'ACESSO BÁSICO')
ON CONFLICT (autoriza_cod) DO NOTHING;

INSERT INTO usuarios (usuario_nm, usuario_ap, cpf_num, email, senha) VALUES
('EVERTON DA SILVA LEANDRO', 'EVERTON', '06247473362', 'everton.leandro1994@gmail.com', '123456789')
ON CONFLICT (cpf_num) DO NOTHING;


INSERT INTO permissoes VALUES ('06247473362','ROLE_ADMIN');