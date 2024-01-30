package com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record NovaCompraPedidoInput(
        @NotNull
        @Positive
        BigDecimal total,
        @NotNull
        List<NovaCompraPedidoItemInput> itens
) {

}
