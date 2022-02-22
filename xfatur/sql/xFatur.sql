/*

drop table if exists license;
drop table if exists person;





create table person(
	id serial,
	first_name varchar(20),
	last_name varchar(20),
	age int,
	primary key (id)
);

create table license(
	id serial,
	type varchar(20),
	valid_from date,
	valid_to date,
	person_id int,
	primary key (id),
	FOREIGN KEY (person_id) REFERENCES person(id)
);


select * from person order by id;

select * from license order by id, person_id ;




*/














/*

drop table if exists phone_number;
drop table if exists customer;


create table customer(
	id serial,
	name varchar(20),
	primary key(id)
);


create table phone_number(
	id serial,
	customer_id int not null,
	number varchar(20),
	type varchar(20),
	primary key(id),
	foreign key (customer_id) references customer(id)
);


select * from customer order by id;

select * from phone_number  order by customer_id;
delete from phone_number;


*/

/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/


/*


drop table if exists bankaccount;

create table bankaccount(
	accno int,
	lastname varchar(25),
	firstname varchar(25),
	bal int
);

insert into bankaccount values(1,'obama','barack',5000);
insert into bankaccount values(2,'donald','trump',4000);

select * from bankaccount;


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/












*/





/*drop table enderecoentrega;
drop table cliente;

create table cliente(
id serial,
nome varchar(50),
primary key(id)
);


create table enderecoentrega(
id integer primary key references cliente(id),
logardouro varchar(100)



);

insert into cliente (nome) values('fdasfad');
insert into enderecoentrega (id, logardouro) values (1, 'rua sem saida');
insert into enderecoentrega (id, logardouro) values (2, 'rua sem saida');



se	lect * from cliente c left join enderecoentrega e on c.id=e.id;
*/

select * from representante;
select * from ramoatividade order by id;
select * from naturezajuridica;
select * from emitente;

select * from destinatario;
select * from enderecoentrega;
select * from enderecoretirada;
select * from enderecocobranca;



select * from  marca;
select * from  origem;
select * from  fundopobreza;
select * from  tipoitem;
select * from  tiposelo;
select * from  tipovalidade;
select * from  regiaoprodutora;
select * from  tributacao;
select * from  classificacaofiscal;
select * from  unidade;
select * from  produtor;
select * from  linha;
select * from  produto;


select * from estoquemensal;

select * from listapreco lp inner join listaprecoitem lpi on lp.id = lpi.listapreco_id;









/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/











select * from information_schema.table_constraints;




DO $$
declare r record;
begin
	raise info '%','----------------------------------------------------------------------------------';
	for r in (select  constraint_name, table_name from  information_schema.table_constraints where constraint_type = 'FOREIGN KEY') loop
	execute CONCAT('ALTER TABLE "' || r.table_name || '" DROP CONSTRAINT '||r.constraint_name);
	raise info '%','dropping '||r.constraint_name;
	end loop;
	raise info '%','----------------------------------------------------------------------------------';
end;
$$;
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*
create function check_estoque(p_input int)
  returns boolean
as
$$
declare
  l_allowed text[] := array['Marine', 'Terrestrial'];
begin
  if p_input = any(l_allowed) then 
    return true;
  end if;
  raise 'The only allowed values are: %', array_to_string(l_allowed, ', ');
end;
$$
language plpgsql
immutable;


*/









/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
drop table if exists emitente;
drop table if exists destinatario;
drop table if exists enderecoentrega;
drop table if exists enderecoretirada;
drop table if exists enderecocobranca;
drop table if exists naturezajuridica;
drop table if exists representante;
drop table if exists ramoatividade;
drop table if exists serie;
drop table if exists nf;


drop table if exists linha;
drop table if exists pais;

drop table if exists estoquemensal;

drop table if exists marca;
drop table if exists origem;
drop table if exists fundopobreza;
drop table if exists tipoitem;
drop table if exists tiposelo;
drop table if exists tipovalidade;
drop table if exists regiaoprodutora;
drop table if exists tributacao;
drop table if exists classificacaofiscal;
drop table if exists unidade;
drop table if exists produtor;
drop table if exists produto;

drop table if exists selo;

drop table if exists listaprecoitem;
drop table if exists listapreco;


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
drop function if exists create_id_emitente_serie_sequence();
drop function if exists numero_nNF();

/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

create table emitente(
	id 		serial,
	CNPJ 		varchar(14) not null, 
	xNome 		varchar(60) not null, 
	xFant 		varchar(60) null, 
	xLgr 		varchar(60) not null, 
	nro 		varchar(60) not null, 
	xCpl 		varchar(60) null, 
	xBairro 	varchar(60)  not null, 
	cMun 		varchar(7)  not null, 
	xMun 		varchar(60) not null, 
	UF 		varchar(2)  not null, 
	CEP 		varchar(8) not null, 
	cPais 		varchar(4) not null, 
	xPais 		varchar(60) not null, 
	fone 		varchar(14) null,
	IE 		varchar(14) not null, 
	IEST 		varchar(14) null, 
	IM 		varchar(15) not null, 
	CNAE 		varchar(7) null,
	CRT 		varchar(1) not null,
	nf_serie_atual	int not null,
	ultima_nnf 	int not null,
	primary key 	(id),
	unique 		(CNPJ)
);
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
create table representante(
	id 		serial not null,
	CNPJCPF 	varchar(14) not null, 
	xNome 		varchar(60) not null,
	xLgr 		varchar(60) not null, 
	nro 		varchar(60) not null, 
	xCpl 		varchar(60) null, 
	xBairro 	varchar(60)  not null, 
	cMun 		varchar(7)  not null, 
	xMun 		varchar(60) not null, 
	UF 		varchar(2)  not null, 
	CEP 		varchar(8) not null, 
	cPais 		varchar(4) not null, 
	xPais 		varchar(60) not null, 
	fone 		varchar(14) null,
	IE 		varchar(14) null, 
	email 		varchar(60) null,
	primary key 	(id),
	unique 		(CNPJCPF)
);
create table ramoatividade(
	id 		serial not null,
	descricao 	varchar(70),
	primary key 	(id),
	unique 		(descricao)
);

create table naturezajuridica(
	id 		serial not null,
	descricao 	varchar (70),
	primary key 	(id),
	unique		(descricao)
);



create table destinatario(
	id 			serial not null,
	CNPJCPF 		varchar(14) not null, 
	idEstrangeiro 		varchar(20) null,
	xNome 			varchar(60) not null,
	xLgr 			varchar(60) not null, 
	nro 			varchar(60) not null, 
	xCpl 			varchar(60) null, 
	xBairro 		varchar(60)  not null, 
	cMun 			varchar(7)  not null, 
	xMun 			varchar(60) not null, 
	UF 			varchar(2)  not null, 
	CEP 			varchar(8) not null, 
	cPais 			varchar(4) null, 
	xPais 			varchar(60) null, 
	fone 			varchar(14) null,
	indIEDest 		varchar(1) not null,
	IE 			varchar(14) null, 
	ISUF 			varchar(9) null,
	IM 			varchar(15) null, 
	email 			varchar(60) null,
	ramoatividade_id 	int not null references ramoatividade,
	naturezajuridica_id 	int not null references naturezajuridica,
	representante_id 	int not null references representante,
	primary key 		(id),
	unique 			(CNPJCPF)
);
create table enderecoentrega(
	id 			serial not null,
	CNPJCPF 		varchar(14) not null, 
	xNome 			varchar(60) not null,
	xLgr 			varchar(60) not null, 
	nro 			varchar(60) not null, 
	xCpl 			varchar(60) null, 
	xBairro 		varchar(60)  not null, 
	cMun 			varchar(7)  not null, 
	xMun 			varchar(60) not null, 
	UF 			varchar(2)  not null, 
	CEP 			varchar(8) not null, 
	cPais 			varchar(4) null, 
	xPais 			varchar(60) null, 
	fone 			varchar(14) null,
	email 			varchar(60) null,
	IE 			varchar(14) null,
	destinatario_id		int not null references destinatario
);
create table enderecoretirada(
	id 			serial not null,
	CNPJCPF 		varchar(14) not null, 
	xNome 			varchar(60) not null,
	xLgr 			varchar(60) not null, 
	nro 			varchar(60) not null, 
	xCpl 			varchar(60) null, 
	xBairro			varchar(60)  not null, 
	cMun 			varchar(7)  not null, 
	xMun 			varchar(60) not null, 
	UF 			varchar(2)  not null, 
	CEP 			varchar(8) not null, 
	cPais 			varchar(4) null, 
	xPais 			varchar(60) null, 
	fone 			varchar(14) null,
	email 			varchar(60) null,
	IE 			varchar(14) null,
	destinatario_id		int not null references destinatario
);

create table enderecocobranca(
	id 			serial not null,
	logradouro 		varchar(40) not null,
	bairro 			varchar(12) null,
	cep 			varchar(8) not null,
	cidade 			varchar(15) not null, 
	estado 			varchar(2) not null,
	destinatario_id 	int not null references destinatario
);


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/




create table linha(
	id 		serial not null,
	descricao 	varchar(80) not null, 
	tipo 		varchar(3) not null,
	ordem 		int not null default 0,
	primary key	(id),
	unique		(descricao)
	
);


create table pais(
	id 		serial not null,
	nome 		varchar(80) not null, 
	sigla		varchar(2) not null,
	origem		varchar(60) not null, 
	codigobacen	varchar(4) not null,
	primary key	(id),
	unique		(codigobacen)
	

);

create table tiposelo(
	id		serial not null,
	codigo		varchar(6) not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(codigo),
	unique 		(descricao)
);
create table tipoitem(
	id		serial not null,
	codigo		varchar(2) not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(codigo),
	unique 		(descricao)
);
create table fundopobreza(
	id		serial not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(descricao)
);
create table origem(
	id		serial not null,
	codigo		varchar(1) not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(codigo),
	unique 		(descricao)
);


create table marca(
	id		serial not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(descricao)
);



create table tipovalidade(
	id		serial not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(descricao)
);


create table regiaoprodutora(
	id		serial not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(descricao)
);


create table tributacao(
	id		serial not null,
	codigo		varchar(2) not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(descricao)
);
create table classificacaofiscal(
	id		serial not null,
	ncm		varchar(9) not null,
	descricao	varchar(80) not null,
	primary key	(id),
	unique 		(ncm),
	unique 		(descricao)
);
create table unidade(
	id		serial not null,
	descricao 	varchar(80) not null,
	abreviacao 	varchar(6) not null,
	primary key 	(id),
	unique 		(abreviacao)
);


create table produtor(
	id 		serial not null,
	descricao 	varchar(80),
	primary key	(id),
	unique 		(descricao)
);




/*---------------------------------------------------------------------------------------------------------------------------*/

create table produto(
	id 			serial not null,
	codigoProduto		varchar(10) not null,
	descricao		varchar(90) not null,
	unidadeDetalhada	varchar(20) not null,
	graduacaoAlcoolica	varchar(15) null,
	pesoLiquido		numeric default 0,
	pesoBruto		numeric default 0,
	codigoDeBarras		varchar(14) null,
	pesoDaCaixa		numeric default 0,
	larguraDaCaixa		numeric default 0,
	comprimentoDaCaixa	numeric default 0,
	ipiUnitario		numeric default 0,
	aliquotaDeReducao	boolean,
	isentoICMS		boolean,
	aliquotaIPI		decimal default 0,
	adquiridoComST		boolean,
	cest			varchar(7) not null,
	estoque			int not null default 0 constraint __estoque_insuficiente__ check(estoque >= 0),
	reservado		int not null default 0 constraint __reservado_insuficiente__ check(reservado >= 0),
	reducaoICMS_id		int,
	iva_id			int,
	
	produtor_id		int not null references produtor,
	unidade_id		int not null references unidade,
	classificacaoFiscal_id	int not null references classificacaofiscal,
	tributacao_id		int not null references tributacao,
	regiaoprodutora_id	int not null references regiaoprodutora,
	linha_id		int not null references linha,
	pais_id			int not null references pais,
	tipovalidade_id		int not null references tipovalidade,
	marca_id		int not null references marca,
	origem_id		int not null references origem,
	tipo_id			int not null references tipoitem,
	fundopobreza_id		int not null references fundopobreza,
	tiposelo_id		int not null references tiposelo,
	primary key 		(id),
	unique 			(codigoProduto)
	
);


/*---------------------------------------------------------------------------------------------------------------------------*/

create table estoquemensal(
	id 			serial not null,
	produto_id 		int not null references produto,
	quantidadeinicial 	int not null default 0 constraint __quantidade_inicial_positiva__ check(quantidadeinicial >= 0),
	mes 			int not null constraint __mes_entre_1_e_12__ check (mes>=1 and mes<=12),
	ano 			int not null constraint __ano_maior_que_0__ check (ano >0),
	custounitario 		decimal not null default 0 constraint __custo_unitario_positivo__ check(custounitario >= 0),
	primary key		(id),
	constraint __produto_mes_e_ano_ja_cadastrado__ unique (produto_id, mes, ano)
);



create table selo(
	id 		serial not null,
	numeroguia 	varchar(6) not null,
	dataguia 	date not null,
	numeroinicial 	varchar(15) not null,
	numerofinal 	varchar(15) not null, 
	substituicao 	boolean default false,
	observacao 	varchar(60),
	codigoselo 	varchar(10),
	produto_id 	int not null references produto,
	primary key	(id),
	constraint __selo_ja_cadastrado__ unique (produto_id, numeroguia)
);

create table listapreco(
	id 			serial not null,
	codigo 			varchar(5) not null,
	data 			date not null default now(),
	tipo 			varchar(1) not null,
	descricao 		varchar(70) not null,
	descontoatacado		decimal,
	descontovista 		decimal,
	desconto21dias 		decimal,
	desconto28dias 		decimal,
	primary key 		(id)
	


);
create table listaprecoitem(
	id 		serial not null, 
	precounitario 	decimal not null constraint __preco_untario_maior_que_zero__ check(precounitario>0),
	tipo 		varchar(5), 
	destacar 	boolean default false,
	produto_id 	int not null references produto,
	listapreco_id 	int not null references listapreco,
	constraint __ja_cadastrado__ unique(id, produto_id),
	primary key	(id)
	
);


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
create table serie(
	id_emitente int references emitente,
	serie int not null,
	nnf_inicial int not null,
	nnf_final int not null,
	primary key (id_emitente, serie)
);





create table nf(
  id serial not null,
  id_emitente integer references emitente,

  cnpj varchar(14) not null,
  serie int not null, --0 a 999
  nNF int not null, --1 a 999.999.999
  
  ind_emitente varchar(1) not null, /*0-Emissão própria;
                      1-Terceiros
                      @CAMPO ICMS IPI
                      */
  
  versao varchar(4) not null,
  chaveNFe varchar(44) not null, -- chave NF-e
  cUF varchar(2) not null, -- CODIGO UF
  cNF varchar(8) not null, --ALEATÓRIO
  natOp varchar(60) not null, 
  mod varchar(2) not null, /*55-NF-e*/
  
  
  dhEmi timestamp not null,
  dhSaiEnt timestamp null,
  tpNF varchar(1) not null, /*0-Entrada
                           1-Saída
			   @CAMPO ICMS IPI - "IND_OPER"
                           */

  idDest varchar(1) not null,/*1-Operação interna;
                            2-Operação interestadual;
                            3-Operação com exterior*/
  cMunFG varchar(7) not null, --Código município
  tpImp varchar(1) not null, /*0-Sem geração de DANFE;
                            1-DANFE normal, Retrato;
                            2-DANFE normal, Paisagem;
                            3-DANFE Simplificado;
                            4-DANFE NFC-e;
                            5-DANFE NFC-e*/
  tpEmis varchar(1) not null, /*1-Emissão normal (não em contingência);
                             2-Contingência FS-IA, com impressão do DANFE em formulário de segurança;
                             3-Contingência SCAN (Sistema de Contingência do Ambiente Nacional);
                             4-Contingência DPEC (Declaração Prévia da Emissão em Contingência);
                             5-Contingência FS-DA, com impressão do DANFE em formulário de segurança;
                             6-Contingência SVC-AN (SEFAZ Virtual de Contingência do AN);
                             7-Contingência SVC-RS (SEFAZ Virtual de Contingência do RS);
                             9-Contingência off-line da NFC-e (as demais opções de contingência são válidas também para a NFC-e).*/


  cDV varchar(1) not null, --DV da Chave de Acesso da NF-e
  tpAmb varchar(1) not null, /*1-Produção
                            2-Homologação*/
  finNFe varchar(1) not null, /*1-NF-e normal;
                             2-NF-e complementar;
                             3-NF-e de ajuste;
                             4-Devolução de mercadoria*/
  indFinal varchar(1) not null, /*0-Normal;
                               1-Consumidor final;*/


  indPres varchar(1) not null, /*0-Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste);
                              1-Operação presencial;
                              2-Operação não presencial, pela Internet;
                              3-Operação não presencial, Teleatendimento;
                              4-NFC-e em operação com entrega a domicílio;
                              9-Operação não presencial, outros.*/

  indIntermed varchar(1) null, 
  procEmi varchar(1) not null, /*0-Emissão de NF-e com aplicativo do contribuinte;
                              1-Emissão de NF-e avulsa pelo Fisco;
                              2-Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco;
                              3-Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco*/
  verProc varchar(20) not null, 
  dhCont timestamp null, --CONTINGÊNCIA data
  xJust varchar(256) null, --CONTINGÊNCIA justificativa de 15 a 256
  unique  (cnpj, serie, nNF),
  primary key (id)
);




/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/









select to_char( round(random() * 99999999),'00000000');



CREATE FUNCTION numero_nNF() RETURNS trigger
  LANGUAGE plpgsql
  AS $$
begin
  if NEW.ind_emitente = '0' then
	NEW.nNF := nextval('id_emitente_'|| NEW.id_emitente ||'_serie_' || NEW.serie);
	NEW.cNF := trim(to_char(round(random() * 99999999),'00000000'));
  elseif NEW.ind_emitente = '1' then
	NEW.tpNF = '0';
  end if;
  RETURN NEW;
end
$$;

CREATE TRIGGER numero_nNF BEFORE INSERT ON nf FOR EACH ROW EXECUTE PROCEDURE numero_nNF();




CREATE FUNCTION create_id_emitente_serie_sequence() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
  execute format('drop sequence if exists id_emitente_%s_serie_%s', NEW.id_emitente, NEW.serie, NEW.nnf_inicial, NEW.nnf_final );
  execute format('create sequence id_emitente_%s_serie_%s minvalue %s  maxvalue %s', NEW.id_emitente, NEW.serie, NEW.nnf_inicial, NEW.nnf_final );
  return NEW;
end
$$;

CREATE TRIGGER create_id_emitente_serie_sequence AFTER INSERT ON serie FOR EACH ROW EXECUTE PROCEDURE create_id_emitente_serie_sequence();


/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/






insert into emitente(CNPJ,xNome,xFant,xLgr,nro,xCpl,xBairro,cMun,xMun,UF,CEP,cPais,xPais,fone,IE,IEST,IM,CNAE,CRT,nf_serie_atual,ultima_nnf) values
           ('00000000000000','empresa 1','','rua sem saida','150','','centro','0000000','são paulo','sp','12345678','1234','brasil','12478','111222333444','','1111111','1234567','2',0,25965);
insert into emitente(CNPJ,xNome,xFant,xLgr,nro,xCpl,xBairro,cMun,xMun,UF,CEP,cPais,xPais,fone,IE,IEST,IM,CNAE,CRT,nf_serie_atual,ultima_nnf) values
           ('11111111111111','empresa 2','','rua sem saida','150','','centro','0000000','são paulo','sp','12345678','1234','brasil','12478','111222333444','','1111111','1234567','2',0,0);



insert into serie(id_emitente, serie, nnf_inicial, nnf_final) values (1,0, 1, 999999999);
insert into serie(id_emitente, serie, nnf_inicial, nnf_final) values (1,1, 5555, 999999999);
--insert into serie(id_emitente, serie, nnf_inicial, nnf_final) values (1,55, 1555, 999999999);




do $$
declare 
   counter integer := 0;
begin
   while counter < 55 loop
	insert into nf (id_emitente,cnpj,serie,nnf,ind_emitente,versao,chaveNFe,cuf,cnf,natop,mod,dhemi,dhsaient,tpnf,iddest,cmunfg,tpimp,tpemis,cdv,tpamb,finnfe,indfinal,indpres,indIntermed,procemi,verproc,dhcont,xjust) values 
	(1,'00000000000000',0,2,0,'1.4','11111111112222222222333333333344444444445555','35','17897897','vendas','55','01/01/2001',null,1,1,1234567,1,1,1,2,1,0,0,null,0,'1.1',null,null);	

	insert into nf (id_emitente,cnpj,serie,nnf,ind_emitente,versao,chaveNFe,cuf,cnf,natop,mod,dhemi,dhsaient,tpnf,iddest,cmunfg,tpimp,tpemis,cdv,tpamb,finnfe,indfinal,indpres,indIntermed,procemi,verproc,dhcont,xjust) values 
	(1,'00000000000000',1,2,0,'1.4','11111111112222222222333333333344444444445555','35','17897897','vendas','55','01/01/2001',null,1,1,1234567,1,1,1,2,1,0,0,null,0,'1.1',null,null);	

	counter := counter + 1;
   end loop;
end$$;
	insert into nf (id_emitente,cnpj,serie,nnf,ind_emitente,versao,chaveNFe,cuf,cnf,natop,mod,dhemi,dhsaient,tpnf,iddest,cmunfg,tpimp,tpemis,cdv,tpamb,finnfe,indfinal,indpres,indIntermed,procemi,verproc,dhcont,xjust) values 
	(1,'11111111111111',0,19999,1,'1.4','11111111112222222222333333333344444444445555','35','17897897','vendas','55','01/01/2001',null,1,1,1234567,1,1,1,2,1,0,0,null,0,'1.1',null,null);	

/*
select * from nf  ;
select * from emitente;

select * from serie;
*/
--delete from serie where serie = 55












