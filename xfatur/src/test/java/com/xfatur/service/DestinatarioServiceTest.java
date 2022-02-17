package com.xfatur.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.xfatur.exception.DestinatarioCNPJCPFExistException;
import com.xfatur.exception.DestinatarioIdNotFoundException;
import com.xfatur.model.Destinatario;
import com.xfatur.model.Endereco;
import com.xfatur.model.EnderecoEntrega;
import com.xfatur.model.EnderecoRetirada;
import com.xfatur.model.Local;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.Pessoa;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;
import com.xfatur.model.test.EnderecoCobranca;
import com.xfatur.testutil.CreateModelTest;

//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
////@TestInstance(Lifecycle.PER_CLASS)
////@TestMethodOrder(OrderAnnotation.class)
class DestinatarioServiceTest {
    Stream<Destinatario> model() {
	return CreateModelTest.destinatarioList();
    }

    Stream<EnderecoEntrega> modelEnderecoEntrega() {
	return CreateModelTest.enderecoEntregaList();
    }

    Stream<EnderecoRetirada> modelEnderecoRetirada() {
	return CreateModelTest.EnderecoRetiradaList();
    }

    Stream<EnderecoCobranca> modelEnderecoCobranca() {
	return CreateModelTest.enderecoCobrancaList();
    }

    @Autowired
    EnderecoCobrancaService enderecoCobrancaService;
    @Autowired
    DestinatarioService destinatarioService;
    @Autowired
    RamoAtividadeService ramoAtividadeService;
    @Autowired
    NaturezaJuridicaService naturezaJuridicaService;
    @Autowired
    RepresentanteService representanteService;

    @Autowired
    EnderecoEntregaService entregaService;

    @Autowired
    EnderecoRetiradaService retiradaService;

    List<Integer> idsRepresentante = new ArrayList<Integer>();
    List<Integer> idsRamoAtividade = new ArrayList<Integer>();
    List<Integer> idsNaturezaJuridica = new ArrayList<Integer>();
    List<Integer> idsDestinatario = new ArrayList<Integer>();

//    @BeforeAll
    void cadastrosAuxiliares() {
	CreateModelTest.ramoAtividadeList().forEach(entity -> CreateModelTest.createAndIds(ramoAtividadeService, entity, idsRamoAtividade));
	CreateModelTest.naturezaJuridicaList().forEach(entity -> CreateModelTest.createAndIds(naturezaJuridicaService, entity, idsNaturezaJuridica));
	CreateModelTest.representanteList().forEach(entity -> CreateModelTest.createAndIds(representanteService, entity, idsRepresentante));
    }

//    @AfterAll
    void deletarCadastrosAuxiliares() {
	idsDestinatario.forEach(id -> destinatarioService.deleteById(id));
	idsRamoAtividade.forEach(id -> ramoAtividadeService.delete(id));
	idsNaturezaJuridica.forEach(id -> naturezaJuridicaService.delete(id));
	idsRepresentante.forEach(id -> representanteService.delete(id));
    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(1)
    void teste_save(Destinatario destinatario) {
	int ramoAtividade_id = CreateModelTest.getCodigoAleatorio(idsRamoAtividade);
	int naturezaJuridica_id = CreateModelTest.getCodigoAleatorio(idsNaturezaJuridica);
	int representante_id = CreateModelTest.getCodigoAleatorio(idsRepresentante);

	RamoAtividade ramoAtividade = ramoAtividadeService.findById(ramoAtividade_id);
	NaturezaJuridica naturezaJuridica = naturezaJuridicaService.findById(naturezaJuridica_id);
	Representante representante = representanteService.findById(representante_id);

	destinatario.setRamoAtividade(ramoAtividade);
	destinatario.setNaturezaJuridica(naturezaJuridica);
	destinatario.setRepresentante(representante);

	Destinatario saved = destinatarioService.save(destinatario);

	MatcherAssert.assertThat(saved.getRamoAtividade(), Matchers.is(destinatario.getRamoAtividade()));
	MatcherAssert.assertThat(saved.getRepresentante(), Matchers.is(destinatario.getRepresentante()));
	MatcherAssert.assertThat(saved.getEnderecoEntrega(), Matchers.is(destinatario.getEnderecoEntrega()));
	MatcherAssert.assertThat(saved.getEnderecoCobranca(), Matchers.is(destinatario.getEnderecoCobranca()));
	MatcherAssert.assertThat(saved.getEnderecoRetirada(), Matchers.is(destinatario.getEnderecoRetirada()));
	MatcherAssert.assertThat(saved.getCNPJCPF(), Matchers.is(destinatario.getCNPJCPF()));
	MatcherAssert.assertThat(saved.getIdEstrangeiro(), Matchers.is(destinatario.getIdEstrangeiro()));
	MatcherAssert.assertThat(saved.getNaturezaJuridica(), Matchers.is(destinatario.getNaturezaJuridica()));
	MatcherAssert.assertThat(saved.getId(), Matchers.is(destinatario.getId()));
	MatcherAssert.assertThat(saved.getxNome(), Matchers.is(destinatario.getxNome()));
	MatcherAssert.assertThat(saved.getIE(), Matchers.is(destinatario.getIE()));
	MatcherAssert.assertThat(saved.getISUF(), Matchers.is(destinatario.getISUF()));
	MatcherAssert.assertThat(saved.getIM(), Matchers.is(destinatario.getIM()));
	MatcherAssert.assertThat(saved.getEmail(), Matchers.is(destinatario.getEmail()));
	MatcherAssert.assertThat(saved.getEnderDest(), Matchers.is(destinatario.getEnderDest()));
	MatcherAssert.assertThat(saved.getIndIEDest(), Matchers.is(destinatario.getIndIEDest()));

	idsDestinatario.add(saved.getId());
    }

//    @ParameterizedTest
//    @MethodSource("model")
//    @Order(2)
    void test_save_cnpjcpf_ja_cadastrado_exception(Destinatario destinatario) {
	Exception exception = Assertions.assertThrows(DestinatarioCNPJCPFExistException.class, () -> {

	    int ramoAtividade_id = CreateModelTest.getCodigoAleatorio(idsRamoAtividade);
	    int naturezaJuridica_id = CreateModelTest.getCodigoAleatorio(idsNaturezaJuridica);
	    int representante_id = CreateModelTest.getCodigoAleatorio(idsRepresentante);

	    RamoAtividade ramoAtividade = ramoAtividadeService.findById(ramoAtividade_id);
	    NaturezaJuridica naturezaJuridica = naturezaJuridicaService.findById(naturezaJuridica_id);
	    Representante representante = representanteService.findById(representante_id);

	    destinatario.setRamoAtividade(ramoAtividade);
	    destinatario.setNaturezaJuridica(naturezaJuridica);
	    destinatario.setRepresentante(representante);

	    destinatarioService.save(destinatario);
	});

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("CNPJ/CPF já cadastrado"));
    }

    // @Test
    @Order(3)
    void test_busca_cnpjcpf_nao_encontrado_exception() {
	Destinatario destinatario = destinatarioService.buscaPorCNPJCPF("456");

	assertNull(destinatario);
    }

    // @Test
    @Order(4)
    void test_busca_cnpjcpf() {
	Destinatario destinatario = destinatarioService.buscaPorCNPJCPF("60977980000109");

	assertNotNull(destinatario);
    }

//    @ParameterizedTest
//    @MethodSource("modelEnderecoCobranca")
//    @Order(5)
    void test_gravar_endereco_cobranca(EnderecoCobranca enderecoCobranca) {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));

	enderecoCobranca.setDestinatario(destinatario);
	enderecoCobranca.setId(destinatario.getId());
	destinatario.setEnderecoCobranca(enderecoCobranca);

	EnderecoCobranca saved = enderecoCobrancaService.save(enderecoCobranca);

	MatcherAssert.assertThat(saved.getCep(), Matchers.is(enderecoCobranca.getCep()));
	MatcherAssert.assertThat(saved.getLogradouro(), Matchers.is(enderecoCobranca.getLogradouro()));
	MatcherAssert.assertThat(saved.getBairro(), Matchers.is(enderecoCobranca.getBairro()));
	MatcherAssert.assertThat(saved.getCidade(), Matchers.is(enderecoCobranca.getCidade()));
	MatcherAssert.assertThat(saved.getEstado(), Matchers.is(enderecoCobranca.getEstado()));

    }

//    @ParameterizedTest
//    @MethodSource("modelEnderecoEntrega")
//    @Order(6)
    void test_gravar_endereco_entrega(EnderecoEntrega enderecoEntrega) {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));

	enderecoEntrega.setDestinatario(destinatario);
	enderecoEntrega.setId(destinatario.getId());
	destinatario.setEnderecoEntrega(enderecoEntrega);

	EnderecoEntrega saved = entregaService.save(enderecoEntrega);

	assertNotNull(saved);
	Destinatario destinatarioSaved = saved.getDestinatario();

	MatcherAssert.assertThat(destinatarioSaved.getCNPJCPF(), Matchers.is(destinatario.getCNPJCPF()));
	Local localSaved = saved.getLocal();
	Local local = enderecoEntrega.getLocal();

	Endereco enderecoSaved = localSaved.getEndereco();
	Endereco endereco = local.getEndereco();

	MatcherAssert.assertThat(enderecoSaved.getxLgr(), Matchers.is(endereco.getxLgr()));

	Pessoa pessoaSaved = localSaved.getPessoa();
	Pessoa pessoa = local.getPessoa();

	MatcherAssert.assertThat(pessoaSaved.getCNPJCPF(), Matchers.is(pessoa.getCNPJCPF()));
	MatcherAssert.assertThat(pessoaSaved.getxNome(), Matchers.is(pessoa.getxNome()));
	MatcherAssert.assertThat(pessoaSaved.getIE(), Matchers.is(pessoa.getIE()));
	MatcherAssert.assertThat(pessoaSaved.getEmail(), Matchers.is(pessoa.getEmail()));

    }

//    @ParameterizedTest
//    @MethodSource("modelEnderecoRetirada")
//    @Order(7)
    void test_gravar_endereco_retirada(EnderecoRetirada enderecoRetirada) {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));

	enderecoRetirada.setDestinatario(destinatario);
	enderecoRetirada.setId(destinatario.getId());
	destinatario.setEnderecoRetirada(enderecoRetirada);

	EnderecoRetirada saved = retiradaService.save(enderecoRetirada);
	assertNotNull(saved);

	Destinatario destinatarioSaved = saved.getDestinatario();

	MatcherAssert.assertThat(destinatarioSaved.getCNPJCPF(), Matchers.is(destinatario.getCNPJCPF()));
	Local localSaved = saved.getLocal();
	Local local = enderecoRetirada.getLocal();

	Endereco enderecoSaved = localSaved.getEndereco();
	Endereco endereco = local.getEndereco();

	MatcherAssert.assertThat(enderecoSaved.getxLgr(), Matchers.is(endereco.getxLgr()));

	Pessoa pessoaSaved = localSaved.getPessoa();
	Pessoa pessoa = local.getPessoa();

	MatcherAssert.assertThat(pessoaSaved.getCNPJCPF(), Matchers.is(pessoa.getCNPJCPF()));
	MatcherAssert.assertThat(pessoaSaved.getxNome(), Matchers.is(pessoa.getxNome()));
	MatcherAssert.assertThat(pessoaSaved.getIE(), Matchers.is(pessoa.getIE()));
	MatcherAssert.assertThat(pessoaSaved.getEmail(), Matchers.is(pessoa.getEmail()));

    }

    // @Test
    @Order(8)
    void test_buscaPorNome() {
	List<Destinatario> destinatarios = destinatarioService.buscaPorNome("a");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.greaterThan(0));
    }

    // @Test
    @Order(9)
    void test_buscaPorNome_tamanho_0() {
	List<Destinatario> destinatarios = destinatarioService.buscaPorNome("aaaaaaaaaaaaaaaa");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.is(0));
    }

    // @Test
    @Order(10)
    void test_findById() {
	idsDestinatario.forEach(id -> {
	    Destinatario destinatario = destinatarioService.findById(id);
	    assertNotNull(destinatario);
	});
    }

    // @Test
    @Order(11)
    void test_findById_NotFoundException() {
	Exception exception = Assertions.assertThrows(DestinatarioIdNotFoundException.class, () -> destinatarioService.findById(10001));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Destinatario não encontrado"));
    }

    // @Test
    @Order(12)
    void test_find_buscaPorIdComEntrega() {
	idsDestinatario.forEach(id -> {
	    Destinatario destinatario = destinatarioService.findById(id);
	    assertNotNull(destinatario);
	});
    }

    // @Test
    @Order(13)
    void test_update_enderecoCobranca() {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));

	EnderecoCobranca enderecoCobranca = destinatario.getEnderecoCobranca();

	if (enderecoCobranca != null) {
	    enderecoCobranca.setCep("88888888");
	    destinatarioService.save(destinatario);
	}
    }

    // @Test
    @Order(14)
    void test_update_enderecoEntrega() {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));

	EnderecoEntrega enderecoEntrega = destinatario.getEnderecoEntrega();

	if (enderecoEntrega != null) {
	    enderecoEntrega.getLocal().getPessoa().setCNPJCPF("11111111111");
	    destinatarioService.save(destinatario);
	}
    }

    // @Test
    @Order(15)
    void test_update_enderecoRetirada() {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(1));

	EnderecoRetirada enderecoRetirada = destinatario.getEnderecoRetirada();

	if (enderecoRetirada != null) {
	    enderecoRetirada.getLocal().getPessoa().setCNPJCPF("22222222222");
	    destinatarioService.save(destinatario);
	}
    }
}