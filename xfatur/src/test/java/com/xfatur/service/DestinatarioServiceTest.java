package com.xfatur.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

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
import com.xfatur.testutil.CreateModelTest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class DestinatarioServiceTest {
    Stream<Destinatario> model() {
	return Stream.of(CreateModelTest.createDestinatarioPJ(), CreateModelTest.createDestinatarioPJI(), CreateModelTest.createDestinatarioPF());
    }

    Stream<EnderecoEntrega> modelEnderecoEntrega() {
	return Stream.of(CreateModelTest.createEnderecoEntrega1());
    }

    Stream<EnderecoRetirada> modelEnderecoRetirada() {
	return Stream.of(CreateModelTest.createEnderecoRetirada1(), CreateModelTest.createEnderecoRetirada2());
    }

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

    @BeforeAll
    void cadastrosAuxiliares() {
	CreateModelTest.ramoAtividadeList().forEach(ra -> {
	    Integer id = ramoAtividadeService.findIdByDescricao(ra.getDescricao());

	    if (id == null) {
		RamoAtividade saved = ramoAtividadeService.save(ra);
		id = saved.getId();
	    }
	    idsRamoAtividade.add(id);
	}

	);
	CreateModelTest.naturezaJuridicaList().forEach(nj -> {
	    Integer id = naturezaJuridicaService.findIdByDescricao(nj.getDescricao());

	    if (id == null) {
		NaturezaJuridica saved = naturezaJuridicaService.save(nj);
		id = saved.getId();
	    }
	    idsNaturezaJuridica.add(id);
	});

	CreateModelTest.representanteList().forEach(r -> {
	    Integer id = representanteService.findIdByCNPJCPF(r.getCNPJCPF());

	    if (id == null) {
		Representante saved = representanteService.save(r);
		id = saved.getId();
	    }
	    idsRepresentante.add(id);
	});
    }

    @AfterAll
    void deletarCadastrosAuxiliares() {
	idsDestinatario.forEach(id -> destinatarioService.deleteById(id));
	idsRamoAtividade.forEach(id -> ramoAtividadeService.delete(id));
	idsNaturezaJuridica.forEach(id -> naturezaJuridicaService.delete(id));
	idsRepresentante.forEach(id -> representanteService.delete(id));
    }

    @ParameterizedTest
    @MethodSource("model")
    @Order(1)
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
	MatcherAssert.assertThat(saved.getEntrega(), Matchers.is(destinatario.getEntrega()));
	MatcherAssert.assertThat(saved.getRetirada(), Matchers.is(destinatario.getRetirada()));
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

    @ParameterizedTest
    @MethodSource("model")
    @Order(2)
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

    @Test
    @Order(3)
    void test_busca_cnpjcpf_nao_encontrado_exception() {
	Destinatario destinatario = destinatarioService.buscaPorCNPJCPF("456");

	assertNull(destinatario);
    }

    @Test
    @Order(4)
    void test_busca_cnpjcpf() {
	Destinatario destinatario = destinatarioService.buscaPorCNPJCPF("60977980000109");

	assertNotNull(destinatario);
    }

    @ParameterizedTest
    @MethodSource("modelEnderecoEntrega")
    @Order(5)
    void test_gravar_entrega(EnderecoEntrega enderecoEntrega) {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(0));

	enderecoEntrega.setDestinatario(destinatario);
	enderecoEntrega.setId(destinatario.getId());
	destinatario.setEntrega(enderecoEntrega);

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

    @ParameterizedTest
    @MethodSource("modelEnderecoRetirada")
    @Order(6)
    void test_gravar_retirada(EnderecoRetirada enderecoRetirada) {
	Destinatario destinatario = destinatarioService.findById(idsDestinatario.get(2));

	enderecoRetirada.setDestinatario(destinatario);
	enderecoRetirada.setId(destinatario.getId());
	destinatario.setRetirada(enderecoRetirada);

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

    public static void main(String[] args) {
	Method[] declaredMethods = Pessoa.class.getDeclaredMethods();
	for (Method method : declaredMethods) {

	    System.out.println(method.getName());
	}
    }

    @Test
    @Order(7)
    void test_buscaPorNome() {
	List<Destinatario> destinatarios = destinatarioService.buscaPorNome("a");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.greaterThan(0));
    }

    @Test
    @Order(8)
    void test_buscaPorNome_tamanho_0() {
	List<Destinatario> destinatarios = destinatarioService.buscaPorNome("aaaaaaaaaaaaaaaa");

	MatcherAssert.assertThat(destinatarios.size(), Matchers.is(0));
    }

    @Test
    @Order(9)
    void test_findById() {
	idsDestinatario.forEach(id -> {
	    Destinatario destinatario = destinatarioService.findById(id);
	    assertNotNull(destinatario);
	});
    }

    @Test
    @Order(10)
    void test_findById_NotFoundException() {
	Exception exception = Assertions.assertThrows(DestinatarioIdNotFoundException.class, () -> destinatarioService.findById(10001));

	MatcherAssert.assertThat(exception.getMessage(), Matchers.is("Destinatario não encontrado"));
    }

    @Test
    @Order(11)
    void test_find_buscaPorIdComEntrega() {
	idsDestinatario.forEach(id -> {
	    Destinatario destinatario = destinatarioService.findById(id);
	    assertNotNull(destinatario);
	});
    }
}