package com.xfatur.dto.projections;

import java.math.BigDecimal;

public interface ProdutoView {
	Integer getId();

	String getCodigoProduto();

	String getDescricao();

	String getUnidadedetalhada();

	String getPais();

	String getProdutor();

	String getCest();

	BigDecimal getIpiUnitario();

	BigDecimal getAliquotaipi();

}
