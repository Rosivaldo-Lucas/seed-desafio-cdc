package com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.detalharcompra;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;

import java.math.BigDecimal;

public record DetalheCompraOutput(
        Long id,
        Boolean cupomAplicado,
        BigDecimal total,
        BigDecimal totalFinal
) {

    public static DetalheCompraOutput criar(final Compra compra) {
        if (!compra.isCupomAplicado()) {
            return new DetalheCompraOutput(compra.getId(), Boolean.FALSE, null, null);
        }

        return new DetalheCompraOutput(compra.getId(), Boolean.TRUE, compra.getPedido().obterTotal(), compra.getPedido().obterTotalFinal());
    }

}
