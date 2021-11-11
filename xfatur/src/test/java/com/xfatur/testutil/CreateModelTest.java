package com.xfatur.testutil;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.model.Emitente;
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

    public static EmitenteDTO createEmitente1() {
	Emitente emitente = new Emitente();
	emitente.setCNPJ("65037603000103");
	emitente.setxNome("Empresa de Viagens Ltda");
	emitente.setxFant("Emp Via");

	Endereco enderecoEmitente = new Endereco();
	enderecoEmitente.setxLgr("Rua Graça Saldanha");
	enderecoEmitente.setNro("150");
	enderecoEmitente.setxCpl("9º - sala 9000");
	enderecoEmitente.setxBairro("Centro");
	enderecoEmitente.setcMun("0000000");
	enderecoEmitente.setxMun("São Paulo");
	enderecoEmitente.setUF("SP");
	enderecoEmitente.setCEP("12345678");
	enderecoEmitente.setcPais("4678");
	enderecoEmitente.setxPais("Brasil");
	enderecoEmitente.setFone("(11) 2254-8787");

	emitente.setEnderEmit(enderecoEmitente);
	emitente.setIE("123456789");
	emitente.setIEST("");
	emitente.setIM("12345678");
	emitente.setCNAE("7654321");
	emitente.setCRT("1");
	emitente.setNf_serie_atual(0);
	emitente.setUltima_nnf(25454);

	return DTOConverter.convert(emitente);

    }

}
