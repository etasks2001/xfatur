package com.xfatur.testutil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UtilCreateProduto {
//    @Autowired
//    private ClassificacaoFiscalService classificacaoFiscalService;
//    @Autowired
//    private UnidadeService unidadeService;
//    @Autowired
//    private ProdutoService produtoService;
//    @Autowired
//    private ProdutorService produtorService;
//    @Autowired
//    private TributacaoService tributacaoService;
//    @Autowired
//    private RegiaoProdutoraService regiaoProdutoraService;
//    @Autowired
//    private TipoValidadeService tipoValidadeService;
//    @Autowired
//    private OrigemService origemService;
//    @Autowired
//    private MarcaService marcaService;
//    @Autowired
//    private TipoItemService tipoService;
//    @Autowired
//    private TipoSeloService tipoSeloService;
//    @Autowired
//    private LinhaService linhaService;
//    @Autowired
//    private PaisService paisService;
//    @Autowired
//    private ModelMapper mapper;

//    private List<Integer> idsTributacao = new ArrayList<Integer>();
//    private List<Integer> idsClassificacaoFiscal = new ArrayList<Integer>();
    private List<Integer> idsProduto = new ArrayList<Integer>();
//    private List<Integer> idsProdutor = new ArrayList<Integer>();
//    private List<Integer> idsUnidade = new ArrayList<Integer>();
//    private List<Integer> idsRegiaoProdutora = new ArrayList<Integer>();
//    private List<Integer> idsTipoValidade = new ArrayList<Integer>();
////    private List<Integer> idsFundoPobreza = new ArrayList<Integer>();
//    private List<Integer> idsOrigem = new ArrayList<Integer>();
//    private List<Integer> idsMarca = new ArrayList<Integer>();
//    private List<Integer> idsTipo = new ArrayList<Integer>();
//    private List<Integer> idsTipoSelo = new ArrayList<Integer>();
//    private List<Integer> idsLinha = new ArrayList<Integer>();
//    private List<Integer> idsPais = new ArrayList<Integer>();

//    public List<Integer> idsProduto() {

//    gravarTabelas();
//	CreateModelTest.produtoList().forEach(produto -> {
//	    int produtor_id = CreateModelTest.getCodigoAleatorio(idsProdutor);
//	    int unidade_id = CreateModelTest.getCodigoAleatorio(idsUnidade);
//	    int classificacaoFiscal_id = CreateModelTest.getCodigoAleatorio(idsClassificacaoFiscal);
//	    int tributacao_id = CreateModelTest.getCodigoAleatorio(idsTributacao);
//	    int regiaoProdutora_id = CreateModelTest.getCodigoAleatorio(idsRegiaoProdutora);
//	    int tipoValidade_id = CreateModelTest.getCodigoAleatorio(idsTipoValidade);
////	    int fundoPobreza_id = CreateModelTest.getCodigoAleatorio(idsFundoPobreza);
//	    int marca_id = CreateModelTest.getCodigoAleatorio(idsMarca);
//	    int origem_id = CreateModelTest.getCodigoAleatorio(idsOrigem);
//	    Integer tipoItem_id = CreateModelTest.getCodigoAleatorio(idsTipo);
//	    Integer tipoSelo_id = CreateModelTest.getCodigoAleatorio(idsTipoSelo);
//	    int linha_id = CreateModelTest.getCodigoAleatorio(idsLinha);
//	    int pais_id = CreateModelTest.getCodigoAleatorio(idsPais);

//	    Produtor produtor = produtorService.findById(produtor_id);
//	    Unidade unidade = unidadeService.findById(unidade_id);
//	    ClassificacaoFiscal classificacaoFiscal = classificacaoFiscalService.findById(classificacaoFiscal_id);
//	    Tributacao tributacao = tributacaoService.findById(tributacao_id);
//	    RegiaoProdutora regiaoProdutora = regiaoProdutoraService.findById(regiaoProdutora_id);
//	    TipoValidade tipoValidade = tipoValidadeService.findById(tipoValidade_id);
//	    Marca marca = marcaService.findById(marca_id);
//	    Origem origem = origemService.findById(origem_id);
//	    TipoItem tipo = tipoService.findById(tipo_id);
//	    TipoSelo tipoSelo = tipoSeloService.findById(tipoSelo_id);
//	    Linha linha = linhaService.findById(linha_id);
//	    Pais pais = paisService.findById(pais_id);

//	    Produtor produtor = new Produtor();
////	    produtor.setId(produtor_id);
//
//	    Unidade unidade = new Unidade();
////	    unidade.setId(unidade_id);
//
//	    ClassificacaoFiscal classificacaoFiscal = new ClassificacaoFiscal();
//	    classificacaoFiscal.setId(classificacaoFiscal_id);
//
//	    Tributacao tributacao = new Tributacao();
//	    tributacao.setId(tributacao_id);
//
//	    RegiaoProdutora regiaoProdutora = new RegiaoProdutora();
//	    regiaoProdutora.setId(regiaoProdutora_id);
//
//	    TipoValidade tipoValidade = new TipoValidade();
//	    tipoValidade.setId(tipoValidade_id);
//
//	    Marca marca = new Marca();
//	    marca.setId(marca_id);
//
//	    Origem origem = new Origem();
//	    origem.setId(origem_id);
//
//	    TipoItem tipoItem = new TipoItem();
//	    tipoItem.setId(tipoItem_id);
//
//	    TipoSelo tipoSelo = new TipoSelo();
//	    tipoSelo.setId(tipoSelo_id);
//
//	    Linha linha = new Linha();
//	    linha.setId(linha_id);
//
//	    Pais pais = new Pais();
//	    pais.setId(pais_id);
//
//	    produto.setProdutor(produtor);
//	    produto.setUnidade(unidade);
//	    produto.setClassificacaoFiscal(classificacaoFiscal);
//	    produto.setTributacao(tributacao);
//	    produto.setRegiaoProdutora(regiaoProdutora);
//	    produto.setTipoValidade(tipoValidade);
//	    produto.setMarca(marca);
//	    produto.setOrigem(origem);
//	    produto.setTipoItem(tipoItem);
//	    produto.setTipoSelo(tipoSelo);
//	    produto.setLinha(linha);
//	    produto.setPais(pais);
//
//	    CreateModelTest.createAndIds(produtoService, produto, idsProduto);
//
////	    produtoService.save(produto);
////	    System.out.println("saved: " + produto.getId());
//
////	    idsProduto.add(produto.getId());
//
//	});
//	System.out.println("==============================================================================================");
//	System.out.println("gravados produtos para teste");
//	System.out.println("==============================================================================================");
//	return (List<Integer>) idsProduto;
//    }

    private void gravarTabelas() {
//	CreateModelTest.createAndIds(produtorService, idsProdutor);

//	CreateModelTest.unidadeList().forEach(entity -> CreateModelTest.createAndIds(unidadeService, entity, idsUnidade));
//	CreateModelTest.classificacaoFiscalList().forEach(entity -> CreateModelTest.createAndIds(classificacaoFiscalService, entity, idsClassificacaoFiscal));
//	CreateModelTest.tributacaoList().forEach(entity -> CreateModelTest.createAndIds(tributacaoService, entity, idsTributacao));
//	CreateModelTest.regiaoProdutoraList().forEach(entity -> CreateModelTest.createAndIds(regiaoProdutoraService, entity, idsRegiaoProdutora));
//	CreateModelTest.tipoValidadeList().forEach(entity -> CreateModelTest.createAndIds(tipoValidadeService, entity, idsTipoValidade));

//	CreateModelTest.origemList().forEach(entity -> CreateModelTest.createAndIds(origemService, entity, idsOrigem));
//	CreateModelTest.marcaList().forEach(entity -> CreateModelTest.createAndIds(marcaService, entity, idsMarca));

//	CreateModelTest.tipoList().forEach(entity -> CreateModelTest.createAndIds(tipoService, entity, idsTipo));
//	CreateModelTest.tipoSeloList().forEach(entity -> CreateModelTest.createAndIds(tipoSeloService, entity, idsTipoSelo));

//	CreateModelTest.linhaList().forEach(entity -> CreateModelTest.createAndIds(linhaService, entity, idsLinha));
//	CreateModelTest.paisList().forEach(entity -> CreateModelTest.createAndIds(paisService, entity, idsPais));
    }

//    public void clear() {
//	idsProduto.forEach(id -> produtoService.deleteById(id));
//	idsTributacao = new ArrayList<Integer>();
//	idsClassificacaoFiscal = new ArrayList<Integer>();
//	idsProduto = new ArrayList<Integer>();
//	idsProdutor = new ArrayList<Integer>();
//	idsUnidade = new ArrayList<Integer>();
//	idsRegiaoProdutora = new ArrayList<Integer>();
//	idsTipoValidade = new ArrayList<Integer>();
////	idsFundoPobreza = new ArrayList<Integer>();
//	idsOrigem = new ArrayList<Integer>();
//	idsMarca = new ArrayList<Integer>();
//	idsTipo = new ArrayList<Integer>();
//	idsTipoSelo = new ArrayList<Integer>();
//	idsLinha = new ArrayList<Integer>();
//	idsPais = new ArrayList<Integer>();
//    }
}
