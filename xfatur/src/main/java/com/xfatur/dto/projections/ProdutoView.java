package com.xfatur.dto.projections;

import java.math.BigDecimal;

public interface ProdutoView {
    Integer getId();

    String getCodigoProduto();

    String getCodigoDeBarras();

    String getDescricao();

    String getUnidadedetalhada();

    String getGraduacaoAlcoolica();

    String getCest();

    BigDecimal getIpiUnitario();

    BigDecimal getAliquotaipi();

    String getPais();

    String getProdutor();

    String getMarca();

    String getRegiaoProdutora();

    String getPesoLiquido();

    String getPesoBruto();

    String getLarguraDaCaixa();

    String getComprimentoDaCaixa();

    String getTipoValidade();

}
