package com.xfatur.testutil;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.xfatur.converter.DTOConverter;
import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.dto.EntregaDTO;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.model.Destinatario;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;
import com.xfatur.model.Entrega;
import com.xfatur.model.IndIEDest;
import com.xfatur.model.Local;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.Pessoa;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;

public class CreateModelTest {

	public static RepresentanteDTO createRepresentante1() {

		Representante representante = new Representante();
		representante.setCNPJCPF("77851609000107");
		representante.setxNome("REPRESENTANTE DE VENDA");
		Endereco endereco = createEndereco2();

		representante.setEndereco(endereco);
		representante.setIE("111222333444");
		representante.setEmail("email@outrosemail.com");

		return DTOConverter.convert(representante);

	}

	private static Endereco createEndereco2() {
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
		return endereco;
	}

	public static RepresentanteDTO createRepresentante2() {

		Representante representante = new Representante();
		representante.setCNPJCPF("54705267000103");
		representante.setxNome("REPRESENTANTE VENDA FACIL");
		Endereco endereco = createEndereco1();

		representante.setEndereco(endereco);
		representante.setIE("444888666888");
		representante.setEmail("email@vendafacil.com.br");

		return DTOConverter.convert(representante);

	}

	private static Endereco createEndereco1() {
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
		return endereco;
	}

	public static EmitenteDTO createEmitente1() {
		Emitente emitente = new Emitente();
		emitente.setCNPJ("65037603000103");
		emitente.setxNome("Empresa de Viagens Ltda");
		emitente.setxFant("Emp Via");

		Endereco enderecoEmitente = createEndereco3();

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

	private static Endereco createEndereco3() {
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
		return enderecoEmitente;
	}

	public static NaturezaJuridicaDTO createNaturezaJuridica1() {
		NaturezaJuridica naturezaJuridica = new NaturezaJuridica();
		naturezaJuridica.setDescricao("EMPRESA PEQUENO PORTE (EPP)");

		return DTOConverter.convert(naturezaJuridica);
	}

	public static NaturezaJuridicaDTO createNaturezaJuridica2() {
		NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

		naturezaJuridica = new NaturezaJuridica();
		naturezaJuridica.setDescricao("MICRO EMPRESA (ME)");
		return DTOConverter.convert(naturezaJuridica);
	}

	public static NaturezaJuridicaDTO createNaturezaJuridica3() {
		NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

		naturezaJuridica = new NaturezaJuridica();
		naturezaJuridica.setDescricao("EMPRESA (OUTRAS)");
		return DTOConverter.convert(naturezaJuridica);

	}

	public static NaturezaJuridicaDTO createNaturezaJuridica4() {
		NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

		naturezaJuridica = new NaturezaJuridica();
		naturezaJuridica.setDescricao("CONSUMIDOR FINAL");
		return DTOConverter.convert(naturezaJuridica);
	}

	public static NaturezaJuridicaDTO createNaturezaJuridica5() {
		NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

		naturezaJuridica = new NaturezaJuridica();
		naturezaJuridica.setDescricao("ENQUADRADO NO SIMPLES NACIONAL");
		return DTOConverter.convert(naturezaJuridica);
	}

	public static RamoAtividadeDTO createRamoAtividade1() {
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("ATACADISTA");
		return DTOConverter.convert(ramoAtividade);
	}

	public static RamoAtividadeDTO createRamoAtividade2() {
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("BAR");

		return DTOConverter.convert(ramoAtividade);
	}

	public static RamoAtividadeDTO createRamoAtividade3() {

		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("BAR/RESTAURANTE");

		return DTOConverter.convert(ramoAtividade);
	}

	public static RamoAtividadeDTO createRamoAtividade4() {
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("BUFFET");
		return DTOConverter.convert(ramoAtividade);
	}

	public static RamoAtividadeDTO createRamoAtividade5() {
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("CONSUMIDOR");
		return DTOConverter.convert(ramoAtividade);

	}

	public static RamoAtividadeDTO createRamoAtividade6() {
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("DIRETO");
		return DTOConverter.convert(ramoAtividade);

	}

	public static DestinatarioDTO createDestinatarioPJ() {
		Destinatario destinatario = new Destinatario();

		Endereco enderDest = createEndereco3();

		destinatario.setCNPJCPF("60977980000109");
		destinatario.setIdEstrangeiro(null);
		destinatario.setxNome("Supermercado do Bairro");
		destinatario.setEnderDest(enderDest);
		destinatario.setIndIEDest(IndIEDest.CONTRIBUINTE_ICMS);
		destinatario.setIE("123456789");
		destinatario.setISUF(null);
		destinatario.setIM(null);
		destinatario.setEmail("mercadodobairro@gmail.com");
		destinatario.setRamoatividade_id(1);
		destinatario.setRepresentante_id(1);
		destinatario.setNaturezajuridica_id(1);

		return DTOConverter.convert(destinatario);
	}

	public static DestinatarioDTO createDestinatarioPJI() {
		Destinatario destinatario = new Destinatario();

		Endereco enderDest = createEndereco1();

		destinatario.setCNPJCPF("80977980000109");
		destinatario.setIdEstrangeiro(null);
		destinatario.setxNome("Assistência Técnica Vapor");
		destinatario.setEnderDest(enderDest);
		destinatario.setIndIEDest(IndIEDest.CONTRIBUINTE_ISENTO_ICMS);
		destinatario.setIE(null);
		destinatario.setISUF(null);
		destinatario.setIM(null);
		destinatario.setEmail("mercadodobairro@gmail.com");
		destinatario.setRamoatividade_id(1);
		destinatario.setRepresentante_id(1);
		destinatario.setNaturezajuridica_id(1);

		return DTOConverter.convert(destinatario);
	}

	public static DestinatarioDTO createDestinatarioPF() {
		Destinatario destinatario = new Destinatario();

		Endereco enderDest = createEndereco2();

		destinatario.setCNPJCPF("12345698777");
		destinatario.setIdEstrangeiro(null);
		destinatario.setxNome("Heitor Augusto");
		destinatario.setEnderDest(enderDest);
		destinatario.setIndIEDest(IndIEDest.NAO_CONTRIBUINTE_ICMS);
		destinatario.setIE("123456789");
		destinatario.setISUF(null);
		destinatario.setIM(null);
		destinatario.setEmail("heitoraugusto@hotmail.com");
		destinatario.setRamoatividade_id(1);
		destinatario.setRepresentante_id(1);
		destinatario.setNaturezajuridica_id(3);

		return DTOConverter.convert(destinatario);
	}

	private static Endereco createEndereco4() {
		Endereco enderDest = new Endereco();
		enderDest.setxLgr("Rua Santa Clara");
		enderDest.setNro("70");
		enderDest.setxCpl("15º andar");
		enderDest.setxBairro("Vila Marieta");
		enderDest.setcMun("3564788");
		enderDest.setxMun("São Paulo");
		enderDest.setUF("SP");
		enderDest.setCEP("45698715");
		enderDest.setcPais("0123");
		enderDest.setxPais("Brasil");
		enderDest.setFone("4478-7887");
		return enderDest;
	}

	public static Stream<RamoAtividadeDTO> ramoAtividadeList() {
		return Stream.of(CreateModelTest.createRamoAtividade1(), CreateModelTest.createRamoAtividade2(), CreateModelTest.createRamoAtividade3(), CreateModelTest.createRamoAtividade4(), CreateModelTest.createRamoAtividade5(), CreateModelTest.createRamoAtividade6());
	}

	public static Stream<NaturezaJuridicaDTO> naturezaJuridicaList() {
		return Stream.of(CreateModelTest.createNaturezaJuridica1(), CreateModelTest.createNaturezaJuridica2(), CreateModelTest.createNaturezaJuridica3(), CreateModelTest.createNaturezaJuridica4(), CreateModelTest.createNaturezaJuridica5());
	}

	public static Stream<RepresentanteDTO> representanteList() {
		return Stream.of(CreateModelTest.createRepresentante1(), CreateModelTest.createRepresentante2());
	}

	public static Integer getCodigoAleatorio(List<Integer> lista) {
		Random rand = new Random();
		int index = rand.nextInt((lista.size() - 0) + 1) + 0;
		index = index >= lista.size() ? index = lista.size() - 1 : index;
		return lista.get(index);

	}

	public static EntregaDTO createEntrega1() {
		Entrega entrega = new Entrega();

		Local local = new Local();
		local.setEndereco(CreateModelTest.createEndereco1());
		local.setPessoa(createPessoa2());
		entrega.setLocal(local);

		return DTOConverter.convert(entrega);
	}

	public static EntregaDTO createEntrega2() {
		Entrega entrega = new Entrega();

		Local local = new Local();
		local.setEndereco(CreateModelTest.createEndereco2());
		local.setPessoa(createPessoa2());
		entrega.setLocal(local);

		return DTOConverter.convert(entrega);
	}

	private static Pessoa createPessoa1() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCNPJCPF("77851609000107");
		pessoa.setxNome("REPRESENTANTE DE VENDA");
		pessoa.setIE("111222333444");
		pessoa.setEmail("email@outrosemail.com");

		return pessoa;
	}

	private static Pessoa createPessoa2() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCNPJCPF("54705267000103");
		pessoa.setxNome("REPRESENTANTE VENDA FACIL");
		pessoa.setIE("444888666888");
		pessoa.setEmail("email@vendafacil.com.br");

		return pessoa;
	}

}
