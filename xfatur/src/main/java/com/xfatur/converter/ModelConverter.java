package com.xfatur.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.dto.EnderecoDTO;
import com.xfatur.dto.EntregaDTO;
import com.xfatur.dto.LocalDTO;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.dto.PessoaDTO;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.dto.RetiradaDTO;
import com.xfatur.model.Destinatario;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;
import com.xfatur.model.EnderecoEntrega;
import com.xfatur.model.Local;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.Pessoa;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;
import com.xfatur.model.EnderecoRetirada;

class ModelConverter {

    public static List<Destinatario> convert(List<DestinatarioDTO> list) {
	return list.stream().map(ModelConverter::convert).collect(Collectors.toList());

    }

    public static Representante convert(RepresentanteDTO representanteDTO) {
	Representante representante = new Representante();

//	representante.setId(representanteDTO.getId());
	representante.setxNome(representanteDTO.getxNome());
//	representante.setDestinatario(ModelConverter.convert(representanteDTO.getDestinatarioDTO()));
	representante.setCNPJCPF(representanteDTO.getCNPJCPF());
	representante.setEndereco(representanteDTO.getEndereco());
	representante.setIE(representanteDTO.getIE());
	representante.setEmail(representanteDTO.getEmail());

	return representante;
    }

    public static RamoAtividade convert(RamoAtividadeDTO ramoAtividadeDTO) {
	RamoAtividade ramoAtividade = new RamoAtividade();

//	ramoAtividade.setId(ramoAtividadeDTO.getId());
	ramoAtividade.setDescricao(ramoAtividadeDTO.getDescricao());
//	ramoAtividade.setDestinatario(ModelConverter.convert(ramoAtividadeDTO.getDestinatarioDTO()));

	return ramoAtividade;
    }

    public static NaturezaJuridica convert(NaturezaJuridicaDTO naturezaJuridicaDTO) {
	NaturezaJuridica naturezaJuridica = new NaturezaJuridica();
//	naturezaJuridica.setId(naturezaJuridicaDTO.getId());
	naturezaJuridica.setDescricao(naturezaJuridicaDTO.getDescricao());
//	if (naturezaJuridicaDTO.getDestinatarioDTO() != null) {
//
//	    naturezaJuridica.setDestinatario(ModelConverter.convert(naturezaJuridicaDTO.getDestinatarioDTO()));
//	}

	return naturezaJuridica;
    }

    public static Endereco convert(EnderecoDTO enderecoDTO) {

	Endereco endereco = new Endereco();

	endereco.setxLgr(enderecoDTO.getxLgr());
	endereco.setNro(enderecoDTO.getNro());
	endereco.setxCpl(enderecoDTO.getxCpl());
	endereco.setxBairro(enderecoDTO.getxBairro());
	endereco.setcMun(enderecoDTO.getcMun());
	endereco.setxMun(enderecoDTO.getxMun());
	endereco.setUF(enderecoDTO.getUF());
	endereco.setCEP(enderecoDTO.getCEP());
	endereco.setcPais(enderecoDTO.getcPais());
	endereco.setxPais(enderecoDTO.getxPais());
	endereco.setFone(enderecoDTO.getFone());

	return endereco;
    }

    public static Emitente convert(EmitenteDTO emitenteDTO) {
	Emitente emitente = new Emitente();

//	emitente.setId(emitenteDTO.getId());
	emitente.setCNPJ(emitenteDTO.getCNPJ());
	emitente.setxNome(emitenteDTO.getxNome());
	emitente.setxFant(emitenteDTO.getxFant());

	emitente.setEnderEmit(convert(emitenteDTO.getEnderEmitDTO()));
	emitente.setIE(emitenteDTO.getIE());
	emitente.setIEST(emitenteDTO.getIEST());
	emitente.setIM(emitenteDTO.getIM());
	emitente.setCNAE(emitenteDTO.getCNAE());
	emitente.setCRT(emitenteDTO.getCRT());
	emitente.setNf_serie_atual(emitenteDTO.getNf_serie_atual());
	emitente.setUltima_nnf(emitenteDTO.getUltima_nnf());

	return emitente;

    }

    public static Destinatario convert(DestinatarioDTO destinatarioDTO) {
	Destinatario destinatario = new Destinatario();

//	destinatario.setId(destinatarioDTO.getId());

	Endereco enderEmit = ModelConverter.convert(destinatarioDTO.getEnderEmitDTO());
	destinatario.setEnderDest(enderEmit);
	destinatario.setCNPJCPF(destinatarioDTO.getCNPJCPF());
	destinatario.setIdEstrangeiro(destinatarioDTO.getIdEstrangeiro());
	destinatario.setIndIEDest(destinatarioDTO.getIndIEDest());
//	destinatario.setRamoAtividade(ModelConverter.convert(destinatarioDTO.getRamoAtividadeDTO()));
//	destinatario.setRepresentante(ModelConverter.convert(destinatarioDTO.getRepresentanteDTO()));
	destinatario.setxNome(destinatarioDTO.getxNome());
	destinatario.setIE(destinatarioDTO.getIE());
	destinatario.setIM(destinatarioDTO.getIM());
	destinatario.setEmail(destinatarioDTO.getEmail());
//	destinatario.setNaturezaJuridica(ModelConverter.convert(destinatarioDTO.getNaturezaJuridicaDTO()));
	destinatario.setISUF(destinatarioDTO.getISUF());
//	destinatario.setRamoatividade_id(destinatarioDTO.getRamoAtividade_id());
//	destinatario.setNaturezajuridica_id(destinatarioDTO.getNaturezaJuridica_id());
//	destinatario.setRepresentante_id(destinatarioDTO.getRepresentante_id());
//		destinatario.setEntrega(ModelConverter.convert(destinatarioDTO.getEntregaDTO()));

	return destinatario;
    }

    public static EnderecoEntrega convert(EntregaDTO entregaDTO) {
	if (entregaDTO == null) {
	    return null;
	}
	EnderecoEntrega entrega = new EnderecoEntrega();
	entrega.setId(entregaDTO.getId());
	entrega.setLocal(ModelConverter.convert(entregaDTO.getLocalDTO()));
	entrega.setDestinatario(ModelConverter.convert(entregaDTO.getDestinatarioDTO()));

	return entrega;
    }

    public static Local convert(LocalDTO localDTO) {
	if (localDTO == null) {
	    return null;
	}
	Local local = new Local();
	local.setEndereco(ModelConverter.convert(localDTO.getEnderecoDTO()));
	local.setPessoa(ModelConverter.convert(localDTO.getPessoaDTO()));

	return local;
    }

    public static Pessoa convert(PessoaDTO pessoaDTO) {
	Pessoa pessoa = new Pessoa();

	pessoa.setCNPJCPF(pessoaDTO.getCNPJCPF());
	pessoa.setxNome(pessoaDTO.getxNome());
	pessoa.setIE(pessoaDTO.getIE());
	pessoa.setEmail(pessoaDTO.getEmail());

	return pessoa;
    }

    public static EnderecoRetirada convert(RetiradaDTO retiradaDTO) {
	EnderecoRetirada retirada = new EnderecoRetirada();

	retirada.setId(retiradaDTO.getId());
	retirada.setLocal(ModelConverter.convert(retiradaDTO.getLocal()));
	retirada.setDestinatario(ModelConverter.convert(retiradaDTO.getDestinatarioDTO()));

	return retirada;
    }

}
