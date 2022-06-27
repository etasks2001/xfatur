delete from usuarios_tem_perfis where usuario_id = (select id from usuario where email = 'email@email.com.br')
delete from usuario where email = 'email@email.com.br'
