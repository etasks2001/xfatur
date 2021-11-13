package com.xfatur.converter;

import com.xfatur.dto.DestinatarioDTO;
import com.xfatur.dto.EmitenteDTO;
import com.xfatur.dto.EnderecoDTO;
import com.xfatur.dto.EntregaDTO;
import com.xfatur.dto.LocalDTO;
import com.xfatur.dto.NaturezaJuridicaDTO;
import com.xfatur.dto.PessoaDTO;
import com.xfatur.dto.RamoAtividadeDTO;
import com.xfatur.dto.RepresentanteDTO;
import com.xfatur.model.Destinatario;
import com.xfatur.model.Emitente;
import com.xfatur.model.Endereco;
import com.xfatur.model.Entrega;
import com.xfatur.model.Local;
import com.xfatur.model.NaturezaJuridica;
import com.xfatur.model.Pessoa;
import com.xfatur.model.RamoAtividade;
import com.xfatur.model.Representante;

public class DTOConverter {

	public static EmitenteDTO convert(Emitente emitente) {
		EmitenteDTO emitenteDTO = new EmitenteDTO();

		emitenteDTO.setId(emitente.getId());
		emitenteDTO.setCNPJ(emitente.getCNPJ());
		emitenteDTO.setxNome(emitente.getxNome());
		emitenteDTO.setxFant(emitente.getxFant());

		emitenteDTO.setEnderEmit(DTOConverter.convert(emitente.getEnderEmit()));
		emitenteDTO.setIE(emitente.getIE());
		emitenteDTO.setIEST(emitente.getIEST());
		emitenteDTO.setIM(emitente.getIM());
		emitenteDTO.setCNAE(emitente.getCNAE());
		emitenteDTO.setCRT(emitente.getCRT());
		emitenteDTO.setNf_serie_atual(emitente.getNf_serie_atual());
		emitenteDTO.setUltima_nnf(emitente.getUltima_nnf());

		return emitenteDTO;
	}

	public static EnderecoDTO convert(Endereco ender) {

		EnderecoDTO enderDTO = new EnderecoDTO();

		enderDTO.setxLgr(ender.getxLgr());
		enderDTO.setNro(ender.getNro());
		enderDTO.setxCpl(ender.getxCpl());
		enderDTO.setxBairro(ender.getxBairro());
		enderDTO.setcMun(ender.getcMun());
		enderDTO.setxMun(ender.getxMun());
		enderDTO.setUF(ender.getUF());
		enderDTO.setCEP(ender.getCEP());
		enderDTO.setcPais(ender.getcPais());
		enderDTO.setxPais(ender.getxPais());
		enderDTO.setFone(ender.getFone());

		return enderDTO;
	}

	public static RamoAtividadeDTO convert(RamoAtividade ramoAtividade) {
		RamoAtividadeDTO ramoAtividadeDTO = new RamoAtividadeDTO();

		ramoAtividadeDTO.setId(ramoAtividade.getId());
		ramoAtividadeDTO.setDescricao(ramoAtividade.getDescricao());
		ramoAtividadeDTO.setDestinatario(ramoAtividade.getDestinatario());

		return ramoAtividadeDTO;
	}

	public static NaturezaJuridicaDTO convert(NaturezaJuridica naturezaJuridica) {
		NaturezaJuridicaDTO naturezaJuridicaDTO = new NaturezaJuridicaDTO();
		naturezaJuridicaDTO.setId(naturezaJuridica.getId());
		naturezaJuridicaDTO.setDescricao(naturezaJuridica.getDescricao());
		naturezaJuridicaDTO.setDestinatario(naturezaJuridicaDTO.getDestinatario());

		return naturezaJuridicaDTO;
	}

	public static RepresentanteDTO convert(Representante representante) {
		RepresentanteDTO representanteDTO = new RepresentanteDTO();

		representanteDTO.setId(representante.getId());
		representanteDTO.setxNome(representante.getxNome());
		representanteDTO.setDestinatario(representante.getDestinatario());
		representanteDTO.setCNPJCPF(representante.getCNPJCPF());
		representanteDTO.setEndereco(representante.getEndereco());
		representanteDTO.setIE(representante.getIE());
		representanteDTO.setEmail(representante.getEmail());

		return representanteDTO;

	}

	public static DestinatarioDTO convert(Destinatario destinatario) {
		DestinatarioDTO destinatarioDTO = new DestinatarioDTO();

		destinatarioDTO.setId(destinatario.getId());

		Endereco enderDest = destinatario.getEnderDest();
		EnderecoDTO enderDestDTO = DTOConverter.convert(enderDest);
		destinatarioDTO.setEnderEmitDTO(enderDestDTO);
		destinatarioDTO.setCNPJCPF(destinatario.getCNPJCPF());
		destinatarioDTO.setIdEstrangeiro(destinatario.getIdEstrangeiro());
		destinatarioDTO.setIndIEDest(destinatario.getIndIEDest());
		destinatarioDTO.setRamoAtividade(destinatario.getRamoAtividade());
		destinatarioDTO.setRepresentante(destinatario.getRepresentante());
		destinatarioDTO.setxNome(destinatario.getxNome());
		destinatarioDTO.setIE(destinatario.getIE());
		destinatarioDTO.setIM(destinatario.getIM());
		destinatarioDTO.setEmail(destinatario.getEmail());
		destinatarioDTO.setNaturezaJuridica(destinatario.getNaturezaJuridica());
		destinatarioDTO.setISUF(destinatario.getISUF());
		destinatarioDTO.setRamoAtividade_id(destinatario.getRamoatividade_id());
		destinatarioDTO.setNaturezaJuridica_id(destinatario.getNaturezajuridica_id());
		destinatarioDTO.setRepresentante_id(destinatario.getRepresentante_id());
//		destinatarioDTO.setEntregaDTO(DTOConverter.convert(destinatario.getEntrega()));

		return destinatarioDTO;
	}

	public static EntregaDTO convert(Entrega entrega) {
		if (entrega == null) {
			return null;
		}
		EntregaDTO entregaDTO = new EntregaDTO();
		entregaDTO.setId(entrega.getId());
		entregaDTO.setLocalDTO(DTOConverter.convert(entrega.getLocal()));

		return entregaDTO;
	}

	private static LocalDTO convert(Local local) {
		LocalDTO localDTO = new LocalDTO();
		localDTO.setEndereco(DTOConverter.convert(local.getEndereco()));
		localDTO.setPessoa(DTOConverter.convert(local.getPessoa()));

		return localDTO;
	}

	private static PessoaDTO convert(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();

		pessoaDTO.setCNPJCPF(pessoa.getCNPJCPF());
		pessoaDTO.setxNome(pessoa.getxNome());
		pessoaDTO.setIE(pessoa.getIE());
		pessoaDTO.setEmail(pessoa.getEmail());

		return pessoaDTO;
	}

}