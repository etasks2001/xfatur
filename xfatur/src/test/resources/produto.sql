DO $$
declare classificacaofiscal_id	int;
declare linha_id 				int;
declare marca_id 				int;
declare origem_id 				int;
declare pais_id 				int;
declare produtor_id 			int;
declare regiaoprodutora_id 		int;
declare tipoitem_id 			int;
declare tiposelo_id 			int;
declare tipovalidade_id 		int;
declare tributacao_id 			int;
declare unidade_id 				int;

begin
	classificacaofiscal_id	 := (select id from classificacaofiscal		 order by random() limit 1);
	linha_id 				 := (select id from linha	 				 order by random() limit 1);
	marca_id 				 := (select id from marca	 				 order by random() limit 1);
	origem_id 				 := (select id from origem	 				 order by random() limit 1);
	pais_id 				 := (select id from pais	 				 order by random() limit 1);
	produtor_id 			 := (select id from produtor	 			 order by random() limit 1);
	regiaoprodutora_id 		 := (select id from regiaoprodutora 		 order by random() limit 1);
	tipoitem_id 			 := (select id from tipoitem				 order by random() limit 1);
	tiposelo_id 			 := (select id from tiposelo	 			 order by random() limit 1);
	tipovalidade_id 		 := (select id from tipovalidade	 		 order by random() limit 1);
	tributacao_id 			 := (select id from tributacao	 			 order by random() limit 1);
	unidade_id 				 := (select id from unidade 				 order by random() limit 1);
insert into produto (codigoproduto, descricao, unidadedetalhada, graduacaoalcoolica, pesoliquido, pesobruto, codigodebarras, pesodacaixa, larguradacaixa, comprimentodacaixa, ipiunitario, aliquotadereducao, isentoicms, aliquotaipi, adquiridocomst, validade, cest, estoque, reservado, reducaoicms_id, iva_id, produtor_id, unidade_id, classificacaofiscal_id, tributacao_id, regiaoprodutora_id, linha_id, pais_id, tipovalidade_id, marca_id, origem_id, tipoitem_id, tiposelo_id) values ('000.11.444','BEBIDA','114456','10%',10.12,15.12,'01234567891234',154.12,10.12,10.12,10.12,'f','f',12,'f',null,'123457',0,0,1,1,produtor_id,unidade_id,classificacaofiscal_id,tributacao_id,regiaoprodutora_id,linha_id,pais_id,tipovalidade_id,marca_id,origem_id,tipoitem_id,tiposelo_id);



	classificacaofiscal_id	 := (select id from classificacaofiscal		 order by random() limit 1);
	linha_id 				 := (select id from linha	 				 order by random() limit 1);
	marca_id 				 := (select id from marca	 				 order by random() limit 1);
	origem_id 				 := (select id from origem	 				 order by random() limit 1);
	pais_id 				 := (select id from pais	 				 order by random() limit 1);
	produtor_id 			 := (select id from produtor	 			 order by random() limit 1);
	regiaoprodutora_id 		 := (select id from regiaoprodutora 		 order by random() limit 1);
	tipoitem_id 			 := (select id from tipoitem				 order by random() limit 1);
	tiposelo_id 			 := (select id from tiposelo	 			 order by random() limit 1);
	tipovalidade_id 		 := (select id from tipovalidade	 		 order by random() limit 1);
	tributacao_id 			 := (select id from tributacao	 			 order by random() limit 1);
	unidade_id 				 := (select id from unidade 				 order by random() limit 1);

insert into produto (codigoproduto, descricao, unidadedetalhada, graduacaoalcoolica, pesoliquido, pesobruto, codigodebarras, pesodacaixa, larguradacaixa, comprimentodacaixa, ipiunitario, aliquotadereducao, isentoicms, aliquotaipi, adquiridocomst, validade, cest, estoque, reservado, reducaoicms_id, iva_id, produtor_id, unidade_id, classificacaofiscal_id, tributacao_id, regiaoprodutora_id, linha_id, pais_id, tipovalidade_id, marca_id, origem_id, tipoitem_id, tiposelo_id) values ('000.11.001','CHOCOLATE ','114456','10%',10.12,15.12,'01234567891234',154.12,10.12,10.12,10.12,'t','t',12,'t',null,'123457',0,0,1,1,produtor_id,unidade_id,classificacaofiscal_id,tributacao_id,regiaoprodutora_id,linha_id,pais_id,tipovalidade_id,marca_id,origem_id,tipoitem_id,tiposelo_id);

	classificacaofiscal_id	 := (select id from classificacaofiscal		 order by random() limit 1);
	linha_id 				 := (select id from linha	 				 order by random() limit 1);
	marca_id 				 := (select id from marca	 				 order by random() limit 1);
	origem_id 				 := (select id from origem	 				 order by random() limit 1);
	pais_id 				 := (select id from pais	 				 order by random() limit 1);
	produtor_id 			 := (select id from produtor	 			 order by random() limit 1);
	regiaoprodutora_id 		 := (select id from regiaoprodutora 		 order by random() limit 1);
	tipoitem_id 			 := (select id from tipoitem				 order by random() limit 1);
	tiposelo_id 			 := (select id from tiposelo	 			 order by random() limit 1);
	tipovalidade_id 		 := (select id from tipovalidade	 		 order by random() limit 1);
	tributacao_id 			 := (select id from tributacao	 			 order by random() limit 1);
	unidade_id 				 := (select id from unidade 				 order by random() limit 1);

insert into produto (codigoproduto, descricao, unidadedetalhada, graduacaoalcoolica, pesoliquido, pesobruto, codigodebarras, pesodacaixa, larguradacaixa, comprimentodacaixa, ipiunitario, aliquotadereducao, isentoicms, aliquotaipi, adquiridocomst, validade, cest, estoque, reservado, reducaoicms_id, iva_id, produtor_id, unidade_id, classificacaofiscal_id, tributacao_id, regiaoprodutora_id, linha_id, pais_id, tipovalidade_id, marca_id, origem_id, tipoitem_id, tiposelo_id) values ('111.11.001','GELEIA','114456','10%',10.12,15.12,'01234567891234',154.12,10.12,10.12,10.12,'t','t',12,'t',null,'123457',0,0,1,1,produtor_id,unidade_id,classificacaofiscal_id,tributacao_id,regiaoprodutora_id,linha_id,pais_id,tipovalidade_id,marca_id,origem_id,tipoitem_id,tiposelo_id);

	classificacaofiscal_id	 := (select id from classificacaofiscal		 order by random() limit 1);
	linha_id 				 := (select id from linha	 				 order by random() limit 1);
	marca_id 				 := (select id from marca	 				 order by random() limit 1);
	origem_id 				 := (select id from origem	 				 order by random() limit 1);
	pais_id 				 := (select id from pais	 				 order by random() limit 1);
	produtor_id 			 := (select id from produtor	 			 order by random() limit 1);
	regiaoprodutora_id 		 := (select id from regiaoprodutora 		 order by random() limit 1);
	tipoitem_id 			 := (select id from tipoitem				 order by random() limit 1);
	tiposelo_id 			 := (select id from tiposelo	 			 order by random() limit 1);
	tipovalidade_id 		 := (select id from tipovalidade	 		 order by random() limit 1);
	tributacao_id 			 := (select id from tributacao	 			 order by random() limit 1);
	unidade_id 				 := (select id from unidade 				 order by random() limit 1);

insert into produto (codigoproduto, descricao, unidadedetalhada, graduacaoalcoolica, pesoliquido, pesobruto, codigodebarras, pesodacaixa, larguradacaixa, comprimentodacaixa, ipiunitario, aliquotadereducao, isentoicms, aliquotaipi, adquiridocomst, validade, cest, estoque, reservado, reducaoicms_id, iva_id, produtor_id, unidade_id, classificacaofiscal_id, tributacao_id, regiaoprodutora_id, linha_id, pais_id, tipovalidade_id, marca_id, origem_id, tipoitem_id, tiposelo_id) values ('999.11.001','FEIJÃO','114456','10%',10.12,15.12,'01234567891234',154.12,10.12,10.12,10.12,'t','t',12,'t',null,'123457',0,0,1,1,produtor_id,unidade_id,classificacaofiscal_id,tributacao_id,regiaoprodutora_id,linha_id,pais_id,tipovalidade_id,marca_id,origem_id,tipoitem_id,tiposelo_id);
	

end;

$$;






