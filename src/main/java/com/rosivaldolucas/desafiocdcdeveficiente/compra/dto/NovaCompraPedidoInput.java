package com.rosivaldolucas.desafiocdcdeveficiente.compra.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.Item;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public record NovaCompraPedidoInput(
        @NotNull
        @Positive
        BigDecimal total,
        @NotNull
        List<NovaCompraPedidoItemInput> itens
) {

    public Function<Compra, Pedido> toModel(final EntityManager entityManager) {
        final List<Item> listaItens = this.itens.stream().map(item -> item.toModel(entityManager)).toList();

        return (compra) -> {
            final Pedido pedidoCriado = Pedido.criar(compra, listaItens);

            if (!pedidoCriado.totalIgual(this.total)) throw new IllegalArgumentException("o total enviado não corresponde ao total calculado.");

            return pedidoCriado;
        };
    }

}
