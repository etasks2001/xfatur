package com.xfatur.testutil;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.model.Endereco;
import com.xfatur.model.Representante;

public class CreateModelTest {

    public static RepresentanteDTO createRepresentante1() {

	Representante representante = new Representante();
	representante.setCNPJCPF("77851609000107");
	representante.setxNome("REPRESENTANTE DE VENDA");
	Endereco endereco = new Endereco();
	endereco.setxLgr("Rua Joaquim Macedo");
	endereco.setNro("350");
	endereco.setxCpl("2º andar");
	endereco.setxBairro("Centro");
	endereco.setcMun("123457");
	endereco.setxMun("São Paulo");
	endereco.setUF("SP");
	endereco.setCEP("1234578");
	endereco.setcPais("1247");
	endereco.setxPais("Brasil");
	endereco.setFone("457978978");

	representante.setEndereco(endereco);
	representante.setIE("111222333444");
	representante.setEmail("email@outrosemail.com");

	return DTOConverter.convert(representante);

    }

    public static RepresentanteDTO createRepresentante2() {

	Representante representante = new Representante();
	representante.setCNPJCPF("54705267000103");
	representante.setxNome("REPRESENTANTE VENDA FACIL");
	Endereco endereco = new Endereco();
	endereco.setxLgr("Rua Sem Saída");
	endereco.setNro("15");
	endereco.setxCpl("1º andar");
	endereco.setxBairro("Vila Marieta");
	endereco.setcMun("7654321");
	endereco.setxMun("Rio de Janeiro");
	endereco.setUF("RJ");
	endereco.setCEP("87654321");
	endereco.setcPais("1247");
	endereco.setxPais("Brasil");
	endereco.setFone("15975387");

	representante.setEndereco(endereco);
	representante.setIE("444888666888");
	representante.setEmail("email@vendafacil.com.br");

	return DTOConverter.convert(representante);

    }

}
