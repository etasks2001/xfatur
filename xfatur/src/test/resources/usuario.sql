insert into usuario (ativo,email,senha) values ('true', 'email@email.com.br','$2a$10$6b6.AaraVa61h484p/do1uE95E9f6kaG5Q3sbcvohY90cxgJLG/AG')
insert into usuarios_tem_perfis (usuario_id, perfil_id) values ((select id from usuario where email = 'email@email.com.br'), 1)
insert into usuarios_tem_perfis (usuario_id, perfil_id) values ((select id from usuario where email = 'email@email.com.br'), 2)
insert into usuarios_tem_perfis (usuario_id, perfil_id) values ((select id from usuario where email = 'email@email.com.br'), 3)