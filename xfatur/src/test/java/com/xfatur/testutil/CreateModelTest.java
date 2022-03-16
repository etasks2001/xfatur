package com.xfatur.testutil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.xfatur.model.Destinatario;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;
import com.xfatur.model.EnderecoEntrega;
import com.xfatur.model.EnderecoRetirada;
import com.xfatur.model.EstoqueMensal;
import com.xfatur.model.IndIEDest;
import com.xfatur.model.Local;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.Pessoa;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;
import com.xfatur.model.Selo;
import com.xfatur.model.test.EnderecoCobranca;
import com.xfatur.service.NaturezaJuridicaService;
import com.xfatur.service.RamoAtividadeService;
import com.xfatur.service.RepresentanteService;
import com.xfatur.service.produto.ClassificacaoFiscalService;
import com.xfatur.service.produto.LinhaService;
import com.xfatur.service.produto.MarcaService;
import com.xfatur.service.produto.OrigemService;
import com.xfatur.service.produto.PaisService;
import com.xfatur.service.produto.ProdutoService;
import com.xfatur.service.produto.ProdutorService;
import com.xfatur.service.produto.RegiaoProdutoraService;
import com.xfatur.service.produto.TipoItemService;
import com.xfatur.service.produto.TipoSeloService;
import com.xfatur.service.produto.TipoValidadeService;
import com.xfatur.service.produto.TributacaoService;
import com.xfatur.service.produto.UnidadeService;
import com.xfatur.validation.dto.cadastro.ClassificacaoFiscalDTO;
import com.xfatur.validation.dto.cadastro.LinhaDTO;
import com.xfatur.validation.dto.cadastro.MarcaDTO;
import com.xfatur.validation.dto.cadastro.OrigemDTO;
import com.xfatur.validation.dto.cadastro.PaisDTO;
import com.xfatur.validation.dto.cadastro.ProdutoDTO;
import com.xfatur.validation.dto.cadastro.ProdutorDTO;
import com.xfatur.validation.dto.cadastro.RegiaoProdutoraDTO;
import com.xfatur.validation.dto.cadastro.TipoItemDTO;
import com.xfatur.validation.dto.cadastro.TipoSeloDTO;
import com.xfatur.validation.dto.cadastro.TipoValidadeDTO;
import com.xfatur.validation.dto.cadastro.TributacaoDTO;
import com.xfatur.validation.dto.cadastro.UnidadeDTO;

public class CreateModelTest {

    public static Representante createRepresentante1() {

	Representante representante = new Representante();
	representante.setCNPJCPF("77851609000107");
	representante.setxNome("REPRESENTANTE DE VENDA");
	Endereco endereco = createEndereco2();

	representante.setEndereco(endereco);
	representante.setIE("111222333444");
	representante.setEmail("email@outrosemail.com");

	return representante;

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

    public static Representante createRepresentante2() {

	Representante representante = new Representante();
	representante.setCNPJCPF("54705267000103");
	representante.setxNome("REPRESENTANTE VENDA FACIL");
	Endereco endereco = createEndereco1();

	representante.setEndereco(endereco);
	representante.setIE("444888666888");
	representante.setEmail("email@vendafacil.com.br");

	return representante;

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

    public static Emitente createEmitente1() {
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

	return emitente;

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

    public static NaturezaJuridica createNaturezaJuridica1() {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("EMPRESA PEQUENO PORTE (EPP)");

	return naturezaJuridica;
    }

    public static NaturezaJuridica createNaturezaJuridica2() {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("MICRO EMPRESA (ME)");
	return naturezaJuridica;
    }

    public static NaturezaJuridica createNaturezaJuridica3() {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("EMPRESA (OUTRAS)");
	return naturezaJuridica;

    }

    public static NaturezaJuridica createNaturezaJuridica4() {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("CONSUMIDOR FINAL");
	return naturezaJuridica;
    }

    public static NaturezaJuridica createNaturezaJuridica5() {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();

	naturezaJuridica = new NaturezaJuridica();
	naturezaJuridica.setDescricao("ENQUADRADO NO SIMPLES NACIONAL");
	return naturezaJuridica;
    }

    public static RamoAtividade createRamoAtividade1() {
	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("ATACADISTA");
	return ramoAtividade;
    }

    public static RamoAtividade createRamoAtividade2() {
	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("BAR");

	return ramoAtividade;
    }

    public static RamoAtividade createRamoAtividade3() {

	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("BAR/RESTAURANTE");

	return ramoAtividade;
    }

    public static RamoAtividade createRamoAtividade4() {
	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("BUFFET");
	return ramoAtividade;
    }

    public static RamoAtividade createRamoAtividade5() {
	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("CONSUMIDOR");
	return ramoAtividade;

    }

    public static RamoAtividade createRamoAtividade6() {
	RamoAtividade ramoAtividade = new RamoAtividade();
	ramoAtividade.setDescricao("DIRETO");
	return ramoAtividade;

    }

    public static Destinatario createDestinatarioPJ() {
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
//	destinatario.setRamoatividade_id(1);
//	destinatario.setRepresentante_id(1);
//	destinatario.setNaturezajuridica_id(1);

	return destinatario;
    }

    public static Destinatario createDestinatarioPJI() {
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
//	destinatario.setRamoatividade_id(1);
//	destinatario.setRepresentante_id(1);
//	destinatario.setNaturezajuridica_id(1);

	return destinatario;
    }

    public static Destinatario createDestinatarioPF() {
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
//	destinatario.setRamoatividade_id(1);
//	destinatario.setRepresentante_id(1);
//	destinatario.setNaturezajuridica_id(3);

	return destinatario;
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

    public static Stream<RamoAtividade> ramoAtividadeList() {
	return Stream.of(CreateModelTest.createRamoAtividade1(), CreateModelTest.createRamoAtividade2(), CreateModelTest.createRamoAtividade3(), CreateModelTest.createRamoAtividade4(),
		CreateModelTest.createRamoAtividade5(), CreateModelTest.createRamoAtividade6());
    }

    public static Stream<NaturezaJuridica> naturezaJuridicaList() {
	return Stream.of(CreateModelTest.createNaturezaJuridica1(), CreateModelTest.createNaturezaJuridica2(), CreateModelTest.createNaturezaJuridica3(), CreateModelTest.createNaturezaJuridica4(),
		CreateModelTest.createNaturezaJuridica5());
    }

    public static Stream<Representante> representanteList() {
	return Stream.of(CreateModelTest.createRepresentante1(), CreateModelTest.createRepresentante2());
    }

    public static <T> T getCodigoAleatorio(List<T> lista) {
	Random rand = new Random();
	int index = rand.nextInt((lista.size() - 0) + 1) + 0;
	index = index >= lista.size() ? index = lista.size() - 1 : index;
	return lista.get(index);

    }

    public static EnderecoEntrega createEnderecoEntrega1() {
	EnderecoEntrega enderecoEntrega = new EnderecoEntrega();

	Local local = new Local();
	local.setEndereco(CreateModelTest.createEndereco1());
	local.setPessoa(createPessoa2());
	enderecoEntrega.setLocal(local);

	return enderecoEntrega;
    }

    public static EnderecoEntrega createEntrega2() {
	EnderecoEntrega entrega = new EnderecoEntrega();

	Local local = new Local();
	local.setEndereco(CreateModelTest.createEndereco2());
	local.setPessoa(createPessoa2());
	entrega.setLocal(local);

	return entrega;
    }

    public static EnderecoEntrega createEntrega3() {
	EnderecoEntrega entrega = new EnderecoEntrega();

	Local local = new Local();
	local.setEndereco(CreateModelTest.createEndereco3());
	local.setPessoa(createPessoa1());
	entrega.setLocal(local);

	return entrega;
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

    public static EnderecoRetirada createEnderecoRetirada1() {
	EnderecoRetirada enderecoRetirada = new EnderecoRetirada();

	Local local = new Local();
	local.setEndereco(CreateModelTest.createEndereco1());
	local.setPessoa(createPessoa2());
	enderecoRetirada.setLocal(local);

	return enderecoRetirada;
    }

    public static EnderecoRetirada createEnderecoRetirada2() {
	EnderecoRetirada enderecoRetirada = new EnderecoRetirada();

	Local local = new Local();
	local.setEndereco(CreateModelTest.createEndereco4());
	local.setPessoa(createPessoa1());
	enderecoRetirada.setLocal(local);

	return enderecoRetirada;
    }

    public static EnderecoCobranca createEnderecoCobranca1() {
	EnderecoCobranca enderecoCobranca = new EnderecoCobranca();
	enderecoCobranca.setLogradouro("Rua Das Graças, 150 - Conj. 457");
	enderecoCobranca.setBairro("Centro");
	enderecoCobranca.setCep("12345678");
	enderecoCobranca.setCidade("São Paulo");
	enderecoCobranca.setEstado("SP");

	return enderecoCobranca;
    }

    public static EnderecoCobranca createEnderecoCobranca2() {
	EnderecoCobranca enderecoCobranca = new EnderecoCobranca();
	enderecoCobranca.setLogradouro("Rua Dos Pescadores, 30 - 1º andar");
	enderecoCobranca.setBairro("Vila SM");
	enderecoCobranca.setCep("87654321");
	enderecoCobranca.setCidade("Rio de Janeiro");
	enderecoCobranca.setEstado("RJ");

	return enderecoCobranca;
    }

    public static ProdutoDTO createProduto1() {
	ProdutoDTO produto = new ProdutoDTO();

	produto.setCodigoProduto("000.11.444");
	produto.setDescricao("BEBIDA");
	produto.setUnidadeDetalhada("114456");
	produto.setGraduacaoAlcoolica("10%");
	produto.setPesoLiquido(new BigDecimal("10.12"));
	produto.setPesoBruto(new BigDecimal("15.12"));
	produto.setCodigoDeBarras("01234567891234");
	produto.setPesoDaCaixa(new BigDecimal("154.12"));
	produto.setLarguraDaCaixa(new BigDecimal("10.12"));
	produto.setComprimentoDaCaixa(new BigDecimal("10.12"));
	produto.setIpiUnitario(new BigDecimal("10.12"));
	produto.setAliquotaDeReducao(Boolean.FALSE);
	produto.setIsentoICMS(Boolean.FALSE);
	produto.setAliquotaipi(new BigDecimal("12"));
	produto.setAdquiridoComST(Boolean.FALSE);
	produto.setCest("123457");
	produto.setEstoque(1111);
	produto.setReservado(2222);
	produto.setReducaoICMS_id(1);
	produto.setIva_id(1);
	produto.setProdutor(null);
	produto.setUnidade(null);
	produto.setClassificacaoFiscal(null);
	produto.setTributacao(null);
	produto.setRegiaoProdutora(null);
	produto.setLinha(null);
	produto.setPais(null);
	produto.setTipoValidade(null);
	produto.setMarca(null);
	produto.setOrigem(null);
	produto.setTipoItem(null);
	produto.setTipoSelo(null);

	return produto;
    }

    public static ProdutoDTO createProduto2() {
	ProdutoDTO produto = new ProdutoDTO();

	produto.setCodigoProduto("000.11.001");
	produto.setDescricao("CHOCOLATE ");
	produto.setUnidadeDetalhada("114456");
	produto.setGraduacaoAlcoolica("10%");
	produto.setPesoLiquido(new BigDecimal("10.12"));
	produto.setPesoBruto(new BigDecimal("15.12"));
	produto.setCodigoDeBarras("01234567891234");
	produto.setPesoDaCaixa(new BigDecimal("154.12"));
	produto.setLarguraDaCaixa(new BigDecimal("10.12"));
	produto.setComprimentoDaCaixa(new BigDecimal("10.12"));
	produto.setIpiUnitario(new BigDecimal("10.12"));
	produto.setAliquotaDeReducao(Boolean.TRUE);
	produto.setIsentoICMS(Boolean.TRUE);
	produto.setAliquotaipi(new BigDecimal("12"));
	produto.setAdquiridoComST(Boolean.TRUE);
	produto.setCest("123457");
	produto.setEstoque(3333);
	produto.setReservado(4444);
	produto.setReducaoICMS_id(1);
	produto.setIva_id(1);
	produto.setProdutor(null);
	produto.setUnidade(null);
	produto.setClassificacaoFiscal(null);
	produto.setTributacao(null);
	produto.setRegiaoProdutora(null);
	produto.setLinha(null);
	produto.setPais(null);
	produto.setTipoValidade(null);
	produto.setMarca(null);
	produto.setOrigem(null);
	produto.setTipoItem(null);
	produto.setTipoSelo(null);

	return produto;
    }

    public static ProdutoDTO createProduto3() {
	ProdutoDTO produto = new ProdutoDTO();

	produto.setCodigoProduto("111.11.001");
	produto.setDescricao("GELEIA");
	produto.setUnidadeDetalhada("114456");
	produto.setGraduacaoAlcoolica("10%");
	produto.setPesoLiquido(new BigDecimal("10.12"));
	produto.setPesoBruto(new BigDecimal("15.12"));
	produto.setCodigoDeBarras("01234567891234");
	produto.setPesoDaCaixa(new BigDecimal("154.12"));
	produto.setLarguraDaCaixa(new BigDecimal("10.12"));
	produto.setComprimentoDaCaixa(new BigDecimal("10.12"));
	produto.setIpiUnitario(new BigDecimal("10.12"));
	produto.setAliquotaDeReducao(Boolean.TRUE);
	produto.setIsentoICMS(Boolean.TRUE);
	produto.setAliquotaipi(new BigDecimal("12"));
	produto.setAdquiridoComST(Boolean.TRUE);
	produto.setCest("123457");
	produto.setEstoque(3333);
	produto.setReservado(4444);
	produto.setReducaoICMS_id(1);
	produto.setIva_id(1);
	produto.setProdutor(null);
	produto.setUnidade(null);
	produto.setClassificacaoFiscal(null);
	produto.setTributacao(null);
	produto.setRegiaoProdutora(null);
	produto.setLinha(null);
	produto.setPais(null);
	produto.setTipoValidade(null);
	produto.setMarca(null);
	produto.setOrigem(null);
	produto.setTipoItem(null);
	produto.setTipoSelo(null);

	return produto;
    }

    public static ProdutoDTO createProduto4() {
	ProdutoDTO produto = new ProdutoDTO();

	produto.setCodigoProduto("999.11.001");
	produto.setDescricao("FEIJÃO");
	produto.setUnidadeDetalhada("114456");
	produto.setGraduacaoAlcoolica("10%");
	produto.setPesoLiquido(new BigDecimal("10.12"));
	produto.setPesoBruto(new BigDecimal("15.12"));
	produto.setCodigoDeBarras("01234567891234");
	produto.setPesoDaCaixa(new BigDecimal("154.12"));
	produto.setLarguraDaCaixa(new BigDecimal("10.12"));
	produto.setComprimentoDaCaixa(new BigDecimal("10.12"));
	produto.setIpiUnitario(new BigDecimal("10.12"));
	produto.setAliquotaDeReducao(Boolean.TRUE);
	produto.setIsentoICMS(Boolean.TRUE);
	produto.setAliquotaipi(new BigDecimal("12"));
	produto.setAdquiridoComST(Boolean.TRUE);
	produto.setCest("123457");
	produto.setEstoque(3333);
	produto.setReservado(4444);
	produto.setReducaoICMS_id(1);
	produto.setIva_id(1);
	produto.setProdutor(null);
	produto.setUnidade(null);
	produto.setClassificacaoFiscal(null);
	produto.setTributacao(null);
	produto.setRegiaoProdutora(null);
	produto.setLinha(null);
	produto.setPais(null);
	produto.setTipoValidade(null);
	produto.setMarca(null);
	produto.setOrigem(null);
	produto.setTipoItem(null);
	produto.setTipoSelo(null);

	return produto;
    }

//    public static Stream<ProdutorDTO> produtorList() {
//	return Stream.of(createProdutor1(), createProdutor2(), createProdutor3());
//    }

    public static Stream<UnidadeDTO> unidadeList() {
	return Stream.of(createUnidade1(), createUnidade2(), createUnidade3(), createUnidade4());
    }

    private static UnidadeDTO createUnidade1() {
	UnidadeDTO unidade = new UnidadeDTO();
	unidade.setAbreviacao("cx");
	unidade.setDescricao("CAIXA");
	return unidade;
    }

    private static UnidadeDTO createUnidade2() {
	UnidadeDTO unidade = new UnidadeDTO();
	unidade.setAbreviacao("kg");
	unidade.setDescricao("QUILOGRAMA");
	return unidade;
    }

    private static UnidadeDTO createUnidade3() {
	UnidadeDTO unidade = new UnidadeDTO();
	unidade.setAbreviacao("lt");
	unidade.setDescricao("LITRO");
	return unidade;
    }

    private static UnidadeDTO createUnidade4() {
	UnidadeDTO unidade = new UnidadeDTO();
	unidade.setAbreviacao("unid");
	unidade.setDescricao("UNIDADE");
	return unidade;
    }

//    public static ProdutorDTO createProdutor1() {
//	ProdutorDTO produtor = new ProdutorDTO();
//	produtor.setDescricao("CERVEJARIA DO SUL");
//	return produtor;
//    }
//
//    public static ProdutorDTO createProdutor2() {
//	ProdutorDTO produtor = new ProdutorDTO();
//	produtor.setDescricao("TOMATES DO JAPÃO");
//	return produtor;
//    }
//
//    public static ProdutorDTO createProdutor3() {
//	ProdutorDTO produtor = new ProdutorDTO();
//	produtor.setDescricao("PESSEGOS DA ITALIA");
//	return produtor;
//    }

    public static Stream<ClassificacaoFiscalDTO> classificacaoFiscalList() {

	return Stream.of(createClassificacaoFiscal1(), createClassificacaoFiscal2(), createClassificacaoFiscal3(), createClassificacaoFiscal4(), createClassificacaoFiscal5(),
		createClassificacaoFiscal6());
    }

    private static ClassificacaoFiscalDTO createClassificacaoFiscal1() {
	ClassificacaoFiscalDTO classificacaoFiscal = new ClassificacaoFiscalDTO();
	classificacaoFiscal.setNcm("12345678");
	classificacaoFiscal.setDescricao("SORVETE DE MASSA");

	return classificacaoFiscal;
    }

    private static ClassificacaoFiscalDTO createClassificacaoFiscal2() {
	ClassificacaoFiscalDTO classificacaoFiscal = new ClassificacaoFiscalDTO();
	classificacaoFiscal.setNcm("45879314");
	classificacaoFiscal.setDescricao("SORVETE DE COPO");

	return classificacaoFiscal;
    }

    private static ClassificacaoFiscalDTO createClassificacaoFiscal3() {
	ClassificacaoFiscalDTO classificacaoFiscal = new ClassificacaoFiscalDTO();
	classificacaoFiscal.setNcm("52975484");
	classificacaoFiscal.setDescricao("SORVETE DE MASSA E COPO");

	return classificacaoFiscal;
    }

    private static ClassificacaoFiscalDTO createClassificacaoFiscal4() {
	ClassificacaoFiscalDTO classificacaoFiscal = new ClassificacaoFiscalDTO();
	classificacaoFiscal.setNcm("15975344");
	classificacaoFiscal.setDescricao("SORVETE DE PALITO");

	return classificacaoFiscal;
    }

    private static ClassificacaoFiscalDTO createClassificacaoFiscal5() {
	ClassificacaoFiscalDTO classificacaoFiscal = new ClassificacaoFiscalDTO();
	classificacaoFiscal.setNcm("55487974");
	classificacaoFiscal.setDescricao("COLHER DE MADEIRA");

	return classificacaoFiscal;
    }

    private static ClassificacaoFiscalDTO createClassificacaoFiscal6() {
	ClassificacaoFiscalDTO classificacaoFiscal = new ClassificacaoFiscalDTO();
	classificacaoFiscal.setNcm("14534564");
	classificacaoFiscal.setDescricao("COPO DE PLASTICO");

	return classificacaoFiscal;
    }

    public static Stream<TributacaoDTO> tributacaoList() {
	return Stream.of(

		createTributacao1(), createTributacao2(), createTributacao3(), createTributacao4(), createTributacao5(), createTributacao6(), createTributacao7(), createTributacao8(),
		createTributacao9(), createTributacao10(), createTributacao11());
    }

    private static TributacaoDTO createTributacao11() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("90");
	tributacao.setDescricao("OUTRAS");
	return tributacao;
    }

    private static TributacaoDTO createTributacao10() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("70");
	tributacao.setDescricao("COM REDUÇÃO DE BASE DE CÁLCULO E COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA");
	return tributacao;
    }

    private static TributacaoDTO createTributacao9() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("60");
	tributacao.setDescricao("ICMS COBRADO ANTERIORMENTE POR SUBSTITUIÇÃO TRIBUTÁRIA");
	return tributacao;
    }

    private static TributacaoDTO createTributacao8() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("51");
	tributacao.setDescricao("DIFERIMENTO");
	return tributacao;
    }

    private static TributacaoDTO createTributacao7() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("50");
	tributacao.setDescricao("SUSPENSÃO");
	return tributacao;
    }

    private static TributacaoDTO createTributacao6() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("41");
	tributacao.setDescricao("NÃO TRIBUTADA");
	return tributacao;
    }

    private static TributacaoDTO createTributacao5() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("40");
	tributacao.setDescricao("ISENTA");
	return tributacao;
    }

    private static TributacaoDTO createTributacao4() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("30");
	tributacao.setDescricao("ISENTA OU NÃO TRIBUTADA E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA");
	return tributacao;
    }

    private static TributacaoDTO createTributacao3() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("20");
	tributacao.setDescricao("COM REDUÇÃO DE BASE DE CÁLCULO");
	return tributacao;
    }

    private static TributacaoDTO createTributacao2() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("10");
	tributacao.setDescricao("TRIBUTADA E COM COBRANÇA DO ICMS POR SUBSTITUIÇÃO TRIBUTÁRIA");
	return tributacao;
    }

    private static TributacaoDTO createTributacao1() {
	TributacaoDTO tributacao = new TributacaoDTO();
	tributacao.setCodigo("00");
	tributacao.setDescricao("TRIBUTADA INTEGRALMENTE");

	return tributacao;
    }

    public static void createAndIds(TributacaoService service, TributacaoDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());

	if (id == null) {
	    id = service.save(entity).getId();
	}
	ids.add(id);
    }

    public static void createAndIds(ClassificacaoFiscalService service, ClassificacaoFiscalDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());

	if (id == null) {

	    ClassificacaoFiscalDTO saved = service.save(entity);
	    id = saved.getId();
	}
	ids.add(id);
    }

    public static void createAndIds(ProdutoService service, ProdutoDTO entity, List<Integer> ids) {
	Integer id = service.findIdByCodigoProduto(entity.getCodigoProduto());

	if (id == null) {
	    id = service.save(entity).getId();

	}
	ids.add(id);
    }

    public static void createAndIds(ProdutorService service, List<Integer> ids) {
	List<ProdutorDTO> all = service.findAll();
	for (ProdutorDTO produtorDTO : all) {
	    ids.add(produtorDTO.getId());
	}
    }

    public static void createAndIds(UnidadeService service, UnidadeDTO entity, List<Integer> ids) {
	Integer id = service.findIdByAbreviacao(entity.getAbreviacao());
	if (id == null) {
	    id = service.save(entity).getId();
	}
	ids.add(id);
    }

    public static void createAndIds(RamoAtividadeService service, RamoAtividade entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());

	if (id == null) {
	    RamoAtividade saved = service.save(entity);
	    id = saved.getId();
	}
	ids.add(id);

    }

    public static void createAndIds(NaturezaJuridicaService service, NaturezaJuridica entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());

	if (id == null) {
	    NaturezaJuridica saved = service.save(entity);
	    id = saved.getId();
	}
	ids.add(id);

    }

    public static void createAndIds(RepresentanteService service, Representante entity, List<Integer> ids) {
	Integer id = service.findIdByCNPJCPF(entity.getCNPJCPF());

	if (id == null) {
	    Representante saved = service.save(entity);
	    id = saved.getId();
	}
	ids.add(id);
    }

    public static void createAndIds(RegiaoProdutoraService service, RegiaoProdutoraDTO entity, List<Integer> ids) {
	Integer id = service.findByIdDescricao(entity.getDescricao());
	if (id == null) {
	    id = service.save(entity).getId();
	}

	ids.add(id);
    }

    public static Stream<Destinatario> destinatarioList() {
	return Stream.of(CreateModelTest.createDestinatarioPJ(), CreateModelTest.createDestinatarioPJI(), CreateModelTest.createDestinatarioPF());
    }

    public static Stream<EnderecoEntrega> enderecoEntregaList() {
	return Stream.of(CreateModelTest.createEnderecoEntrega1());
    }

    public static Stream<EnderecoRetirada> EnderecoRetiradaList() {
	return Stream.of(CreateModelTest.createEnderecoRetirada1());
    }

    public static Stream<EnderecoCobranca> enderecoCobrancaList() {
	return Stream.of(CreateModelTest.createEnderecoCobranca1());
    }

    public static Stream<RegiaoProdutoraDTO> regiaoProdutoraList() {
	return Stream.of(createRegiaoProdutora1(), createRegiaoProdutora2(), createRegiaoProdutora3(), createRegiaoProdutora4(), createRegiaoProdutora5());
    }

    private static RegiaoProdutoraDTO createRegiaoProdutora1() {
	RegiaoProdutoraDTO regiaoProdutora = new RegiaoProdutoraDTO();
	regiaoProdutora.setDescricao("ZONA DA MATA");
	return regiaoProdutora;
    }

    private static RegiaoProdutoraDTO createRegiaoProdutora2() {
	RegiaoProdutoraDTO regiaoProdutora = new RegiaoProdutoraDTO();
	regiaoProdutora.setDescricao("SUL DE MINAS GERAIS");
	return regiaoProdutora;
    }

    private static RegiaoProdutoraDTO createRegiaoProdutora3() {
	RegiaoProdutoraDTO regiaoProdutora = new RegiaoProdutoraDTO();
	regiaoProdutora.setDescricao("VALE DO PARANAPIACABA");
	return regiaoProdutora;
    }

    private static RegiaoProdutoraDTO createRegiaoProdutora4() {
	RegiaoProdutoraDTO regiaoProdutora = new RegiaoProdutoraDTO();
	regiaoProdutora.setDescricao("VALE DO SAO FRANCISCO");
	return regiaoProdutora;
    }

    private static RegiaoProdutoraDTO createRegiaoProdutora5() {
	RegiaoProdutoraDTO regiaoProdutora = new RegiaoProdutoraDTO();
	regiaoProdutora.setDescricao("NORTE DE SAO PAULO");
	return regiaoProdutora;
    }

    public static Stream<TipoValidadeDTO> tipoValidadeList() {
	return Stream.of(createTipoValidade1(), createTipoValidade2(), createTipoValidade3());
    }

    private static TipoValidadeDTO createTipoValidade1() {
	TipoValidadeDTO tipoValidade = new TipoValidadeDTO();
	tipoValidade.setDescricao("INDETERMINADA");
	return tipoValidade;
    }

    private static TipoValidadeDTO createTipoValidade2() {
	TipoValidadeDTO tipoValidade = new TipoValidadeDTO();
	tipoValidade.setDescricao("IMPERECÍVEL");
	return tipoValidade;
    }

    private static TipoValidadeDTO createTipoValidade3() {
	TipoValidadeDTO tipoValidade = new TipoValidadeDTO();
	tipoValidade.setDescricao("OUTRA");
	return tipoValidade;
    }

    public static void createAndIds(TipoValidadeService service, TipoValidadeDTO entity, List<Integer> ids) {
	Integer id = service.findByIdDescricao(entity.getDescricao());
	if (id == null) {
	    id = service.save(entity).getId();
	}

	ids.add(id);
    }

    public static Stream<MarcaDTO> marcaList() {

	return Stream.of(createMarca1(), createMarca2(), createMarca3(), createMarca4(), createMarca5());
    }

    private static MarcaDTO createMarca1() {
	MarcaDTO marca = new MarcaDTO();
	marca.setDescricao("SEM MARCA");
	return marca;
    }

    private static MarcaDTO createMarca2() {
	MarcaDTO marca = new MarcaDTO();
	marca.setDescricao("DOCE DE LEITE");
	return marca;
    }

    private static MarcaDTO createMarca3() {
	MarcaDTO marca = new MarcaDTO();
	marca.setDescricao("LEITE CONDENSADO");
	return marca;
    }

    private static MarcaDTO createMarca4() {
	MarcaDTO marca = new MarcaDTO();
	marca.setDescricao("DOCE DE AMORA");
	return marca;
    }

    private static MarcaDTO createMarca5() {
	MarcaDTO marca = new MarcaDTO();
	marca.setDescricao("CALDO DE LIMAO");
	return marca;
    }

    public static void createAndIds(MarcaService service, MarcaDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());
	if (id == null) {
	    MarcaDTO saved = service.save(entity);
	    id = saved.getId();
	}

	ids.add(id);
    }

    public static Stream<OrigemDTO> origemList() {

	return Stream.of(createOrigem1(), createOrigem2(), createOrigem3(), createOrigem4(), createOrigem5());
    }

    private static OrigemDTO createOrigem1() {
	OrigemDTO origem = new OrigemDTO();
	origem.setId(0);
	origem.setCodigo("0");
	origem.setDescricao("NACIONAL");

	return origem;
    }

    private static OrigemDTO createOrigem2() {
	OrigemDTO origem = new OrigemDTO();
	origem.setId(1);
	origem.setCodigo("1");
	origem.setDescricao("ESTRANGEIRA-IMPORT. DIRETA");

	return origem;
    }

    private static OrigemDTO createOrigem3() {
	OrigemDTO origem = new OrigemDTO();
	origem.setId(2);
	origem.setCodigo("2");
	origem.setDescricao("ESTRANGEIRA-ADQ.MERCADO INTERNO");

	return origem;
    }

    private static OrigemDTO createOrigem4() {
	OrigemDTO origem = new OrigemDTO();
	origem.setId(6);
	origem.setCodigo("3");

	origem.setDescricao("(LISTA CAMEX) ESTRANGEIRA-IMPORT. DIRETA");

	return origem;
    }

    private static OrigemDTO createOrigem5() {
	OrigemDTO origem = new OrigemDTO();
	origem.setCodigo("5");
	origem.setId(7);
	origem.setDescricao("(LISTA CAMEX) ESTRANGEIRA-ADQ.MERCADO INTERNO");

	return origem;
    }

    public static void createAndIds(OrigemService service, OrigemDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());
	if (id == null) {
	    id = service.save(entity).getId();

	}

	ids.add(id);
    }

    public static Stream<TipoItemDTO> tipoList() {
	return Stream.of(createTipo1(), createTipo2(), createTipo3(), createTipo4(), createTipo5(), createTipo6(), createTipo7(), createTipo8(), createTipo9(), createTipo10(), createTipo11(),
		createTipo12());
    }

    private static TipoItemDTO createTipo12() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("99");
	tipo.setDescricao("OUTRAS");
	return tipo;
    }

    private static TipoItemDTO createTipo11() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("10");
	tipo.setDescricao("OUTROS INSUMOS");
	return tipo;
    }

    private static TipoItemDTO createTipo10() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("09");
	tipo.setDescricao("SERVIÇOS");
	return tipo;
    }

    private static TipoItemDTO createTipo9() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("08");
	tipo.setDescricao("ATIVO IMOBILIZADO");
	return tipo;
    }

    private static TipoItemDTO createTipo8() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("07");
	tipo.setDescricao("MATERIAL DE USO E CONSUMO");
	return tipo;
    }

    private static TipoItemDTO createTipo7() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("06");
	tipo.setDescricao("PRODUTO INTERMEDIÁRIO");
	return tipo;
    }

    private static TipoItemDTO createTipo6() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("05");
	tipo.setDescricao("SUBPRODUTO");
	return tipo;
    }

    private static TipoItemDTO createTipo5() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("04");
	tipo.setDescricao("PRODUTO ACABADO");
	return tipo;
    }

    private static TipoItemDTO createTipo4() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("03");
	tipo.setDescricao("PRODUTO EM PROCESSO");
	return tipo;
    }

    private static TipoItemDTO createTipo3() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("02");
	tipo.setDescricao("EMBALAGEM");
	return tipo;
    }

    private static TipoItemDTO createTipo2() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("01");
	tipo.setDescricao("MATÉRIA-PRIMA");
	return tipo;
    }

    private static TipoItemDTO createTipo1() {
	TipoItemDTO tipo = new TipoItemDTO();
	tipo.setCodigo("00");
	tipo.setDescricao("MERCADORIA PARA REVENDA");
	return tipo;
    }

    public static void createAndIds(TipoItemService service, TipoItemDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());
	if (id == null) {
	    id = service.save(entity).getId();

	}

	ids.add(id);
    }

    public static Stream<TipoSeloDTO> tipoSeloList() {
	return Stream.of(createTipoSelo1(), createTipoSelo2(), createTipoSelo3(), createTipoSelo4(), createTipoSelo5(), createTipoSelo6());
    }

    private static TipoSeloDTO createTipoSelo6() {
	TipoSeloDTO tipoSelo = new TipoSeloDTO();
	tipoSelo.setCodigo("971012");
	tipoSelo.setDescricao("971012-Nacional p/Exp.-Tipo3-Verde Escuro combinado c/marrom");
	return tipoSelo;
    }

    private static TipoSeloDTO createTipoSelo5() {
	TipoSeloDTO tipoSelo = new TipoSeloDTO();
	tipoSelo.setCodigo("971011");
	tipoSelo.setDescricao("971011-Nacional p/Exp.-Tipo2-Verde Escuro combinado c/marrom");
	return tipoSelo;
    }

    private static TipoSeloDTO createTipoSelo4() {
	TipoSeloDTO tipoSelo = new TipoSeloDTO();
	tipoSelo.setCodigo("971010");
	tipoSelo.setDescricao("971010-Nacional p/Exp.-Tipo1-Verde Escuro combinado c/marrom");
	return tipoSelo;
    }

    private static TipoSeloDTO createTipoSelo3() {
	TipoSeloDTO tipoSelo = new TipoSeloDTO();
	tipoSelo.setCodigo("971001");
	tipoSelo.setDescricao("971001-Nacional-Verde combinado com marrom");
	return tipoSelo;
    }

    private static TipoSeloDTO createTipoSelo2() {
	TipoSeloDTO tipoSelo = new TipoSeloDTO();
	tipoSelo.setCodigo("861009");
	tipoSelo.setDescricao("861009-Estrangeiro - Vermelho combinado com azul");
	return tipoSelo;
    }

    private static TipoSeloDTO createTipoSelo1() {
	TipoSeloDTO tipoSelo = new TipoSeloDTO();
	tipoSelo.setCodigo("100000");
	tipoSelo.setDescricao("SEM SELO");
	return tipoSelo;
    }

    public static void createAndIds(TipoSeloService service, TipoSeloDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());
	if (id == null) {
	    id = service.save(entity).getId();
	}

	ids.add(id);
    }

    public static Stream<PaisDTO> paisList() {
	return Stream.of(createPais1(), createPais2(), createPais3(), createPais4(), createPais5());

    }

    private static PaisDTO createPais1() {
	PaisDTO pais = new PaisDTO();
	pais.setNome("BRASIL");
	pais.setOrigem("BRASILEIRA");
	pais.setCodigoBacen("1058");
	pais.setSigla("BR");
	return pais;
    }

    private static PaisDTO createPais2() {
	PaisDTO pais = new PaisDTO();
	pais.setNome("GRÉCIA");
	pais.setOrigem("GREGA");
	pais.setCodigoBacen("3018");
	pais.setSigla("GR");
	return pais;
    }

    private static PaisDTO createPais3() {
	PaisDTO pais = new PaisDTO();
	pais.setNome("HOLANDA");
	pais.setOrigem("HOLANDESA");
	pais.setCodigoBacen("5738");
	pais.setSigla("HO");
	return pais;
    }

    private static PaisDTO createPais4() {
	PaisDTO pais = new PaisDTO();
	pais.setNome("HUNGRIA");
	pais.setOrigem("HÚNGARA");
	pais.setCodigoBacen("3557");
	pais.setSigla("HU");
	return pais;
    }

    private static PaisDTO createPais5() {
	PaisDTO pais = new PaisDTO();
	pais.setNome("ÍNDIA");
	pais.setOrigem("INDIANA");
	pais.setCodigoBacen("3611");
	pais.setSigla("ID");
	return pais;
    }

    public static Stream<LinhaDTO> linhaList() {
	return Stream.of(createLinha1(), createLinha2(), createLinha3(), createLinha4(), createLinha5());
    }

    private static LinhaDTO createLinha1() {
	LinhaDTO linha = new LinhaDTO();
	linha.setDescricao("AMENDOIN");
	linha.setOrdem(1);
	linha.setTipo("COM");
	return linha;
    }

    private static LinhaDTO createLinha2() {
	LinhaDTO linha = new LinhaDTO();
	linha.setDescricao("ARROZ");
	linha.setOrdem(2);
	linha.setTipo("ALI");
	return linha;
    }

    private static LinhaDTO createLinha3() {
	LinhaDTO linha = new LinhaDTO();
	linha.setDescricao("NOS MOSCADA");
	linha.setOrdem(3);
	linha.setTipo("CER");
	return linha;
    }

    private static LinhaDTO createLinha4() {
	LinhaDTO linha = new LinhaDTO();
	linha.setDescricao("GELEIA");
	linha.setOrdem(4);
	linha.setTipo("GEL");
	return linha;
    }

    private static LinhaDTO createLinha5() {
	LinhaDTO linha = new LinhaDTO();
	linha.setDescricao("ALCOOL");
	linha.setOrdem(5);
	linha.setTipo("LIM");
	return linha;
    }

    public static void createAndIds(LinhaService service, LinhaDTO entity, List<Integer> ids) {
	Integer id = service.findIdByDescricao(entity.getDescricao());
	if (id == null) {
	    LinhaDTO saved = service.save(entity);
	    id = saved.getId();
	}

	ids.add(id);
    }

    public static void createAndIds(PaisService service, PaisDTO entity, List<Integer> ids) {
	Integer id = service.findIdByCodigoBacen(entity.getCodigoBacen());
	if (id == null) {
	    id = service.save(entity).getId();

	}

	ids.add(id);
    }

    public static Stream<ProdutoDTO> produtoList() {
	return Stream.of(CreateModelTest.createProduto1(), CreateModelTest.createProduto2(), CreateModelTest.createProduto3(), CreateModelTest.createProduto4());
    }

    public static Stream<EstoqueMensal> estoqueMensalList() {

	return Stream.of(createEstoqueMensal1(), createEstoqueMensal2(), createEstoqueMensal3(), createEstoqueMensal4());
    }

    private static EstoqueMensal createEstoqueMensal1() {
	EstoqueMensal estoqueMensal = new EstoqueMensal();
	estoqueMensal.setAno(2001);
	estoqueMensal.setMes(1);
	estoqueMensal.setQuantidadeInicial(151);
	estoqueMensal.setCustoUnitario(new BigDecimal("154.44"));
	return estoqueMensal;
    }

    private static EstoqueMensal createEstoqueMensal2() {
	EstoqueMensal estoqueMensal = new EstoqueMensal();
	estoqueMensal.setAno(2001);
	estoqueMensal.setMes(1);
	estoqueMensal.setQuantidadeInicial(263);
	estoqueMensal.setCustoUnitario(new BigDecimal("308.88"));
	return estoqueMensal;
    }

    private static EstoqueMensal createEstoqueMensal3() {
	EstoqueMensal estoqueMensal = new EstoqueMensal();
	estoqueMensal.setAno(2001);
	estoqueMensal.setMes(1);
	estoqueMensal.setQuantidadeInicial(374);
	estoqueMensal.setCustoUnitario(new BigDecimal("316.99"));
	return estoqueMensal;
    }

    private static EstoqueMensal createEstoqueMensal4() {
	EstoqueMensal estoqueMensal = new EstoqueMensal();
	estoqueMensal.setAno(2001);
	estoqueMensal.setMes(1);
	estoqueMensal.setQuantidadeInicial(485);
	estoqueMensal.setCustoUnitario(new BigDecimal("732"));
	return estoqueMensal;
    }

    public static Stream<Selo> seloList() {
	return Stream.of(createSelo1(), createSelo2(), createSelo3());
    }

    private static Selo createSelo1() {
	Selo selo = new Selo();
	selo.setNumeroGuia("259/98");
	selo.setDataGuia(LocalDate.parse("04/11/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	selo.setNumeroInicial("28446664");
	selo.setNumeroFinal("28447863");
	selo.setSubstituicao(Boolean.FALSE);
	selo.setObservacao("");
	selo.setCodigoSelo("643715");
	return selo;
    }

    private static Selo createSelo2() {
	Selo selo = new Selo();
	selo.setNumeroGuia("044/02");
	selo.setDataGuia(LocalDate.parse("14/03/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	selo.setNumeroInicial("01429663");
	selo.setNumeroFinal("01430862");
	selo.setSubstituicao(Boolean.FALSE);
	selo.setObservacao("");
	selo.setCodigoSelo("863715");
	return selo;
    }

    private static Selo createSelo3() {
	Selo selo = new Selo();
	selo.setNumeroGuia("145/02");
	selo.setDataGuia(LocalDate.parse("22/11/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	selo.setNumeroInicial("23126140");
	selo.setNumeroFinal("23157870");
	selo.setSubstituicao(Boolean.FALSE);
	selo.setObservacao("");
	selo.setCodigoSelo("863715");
	return selo;
    }

}
