package com.xfatur.service.produto;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfatur.dto.produto.ProdutoDTO;
import com.xfatur.dto.projections.ProdutoView;
import com.xfatur.exception.ProdutoCodigoNotFoundException;
import com.xfatur.exception.ProdutoEstoqueInsuficienteException;
import com.xfatur.exception.ProdutoIdNotFoundException;
import com.xfatur.exception.ProdutoReservadoInsuficienteException;
import com.xfatur.mappers.ModelMapper;
import com.xfatur.model.produto.Produto;
import com.xfatur.repository.produto.ProdutoRepository;
import com.xfatur.util.Util;

@Service
@Transactional(readOnly = true)
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Transactional(readOnly = false)
	public Produto save(Produto produto) {
		return repository.save(produto);
	}

	@Transactional(readOnly = false)
	public void deleteById(Integer id) {
		repository.deleteById(id);

	}

	public Produto findById(Integer id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isPresent()) {
			return produto.get();
		}

		throw new ProdutoIdNotFoundException("Produto não encontrado.");
	}

	public Page<ProdutoView> findByDescricao(String descricao, Pageable pageable) {
		return repository.findByDescricao(descricao, pageable);
	}

	public Produto findByCodigoProduto(String codigoProduto) {

		Produto found = repository.findByCodigoProduto(codigoProduto);
		if (found != null) {
			return found;
		}

		throw new ProdutoCodigoNotFoundException("Código do Produto não encontrado");
	}

	public void entradaEstoque(Integer id, Integer quantidade) {
		repository.entradaEstoque(id, quantidade);
	}

	public void entradaReservado(Integer id, Integer quantidade) {
		repository.entradaReservado(id, quantidade);
	}

	public void saidaEstoque(Integer id, Integer quantidade) {
		try {
			repository.saidaEstoque(id, quantidade);
		} catch (Exception e) {
			throw new ProdutoEstoqueInsuficienteException(Util.extractContraintMessage(e));
		}
	}

	public void saidaReservado(Integer id, Integer quantidade) {
		try {
			repository.saidaReservado(id, quantidade);
		} catch (Exception e) {
			throw new ProdutoReservadoInsuficienteException(Util.extractContraintMessage(e));
		}
	}

	public Integer findIdByCodigoProduto(String codigoProduto) {
		return repository.findIdByCodigoProduto(codigoProduto);
	}

	@Transactional(readOnly = false)
	public void update(@Valid ProdutoDTO dto) {
		repository.update(dto.getId(), dto.getCodigoProduto());

	}

	public Boolean hasCodigoProduto(Integer id, String codigoProduto) {
		return repository.hasCodigoProduto(id, codigoProduto);
	}

}