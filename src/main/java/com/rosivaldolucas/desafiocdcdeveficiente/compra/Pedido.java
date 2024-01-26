package com.rosivaldolucas.desafiocdcdeveficiente.compra;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    private BigDecimal totalFinal;

    @ElementCollection
    private final List<Item> itens = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "ID_COMPRA")
    private Compra compra;

    protected Pedido() { }

    private Pedido(final Long id, final Compra compra, final List<Item> itens) {
        this.id = id;
        this.compra = compra;
        this.itens.addAll(itens);
    }

    public static Pedido criar(final Compra compra, final List<Item> itens) {
        final Pedido novoPedido = new Pedido(null, compra, itens);

        novoPedido.calcularTotalItens();

        return novoPedido;
    }

    public boolean totalIgual(final BigDecimal total) {
        return total.compareTo(this.total) == 0;
    }

    public void aplicarPercentualDesconto(final BigDecimal percentualDesconto) {
        final BigDecimal cemPorCento = BigDecimal.valueOf(100);
        final BigDecimal valorDesconto = this.total.multiply(percentualDesconto.divide(cemPorCento, RoundingMode.HALF_UP));

        this.totalFinal = this.total.subtract(valorDesconto);
    }

    public BigDecimal obterTotal() {
        return this.total;
    }

    public BigDecimal obterTotalFinal() {
        return this.totalFinal;
    }

    private void calcularTotalItens() {
        BigDecimal totalItens = BigDecimal.ZERO;

        for (final Item item : this.itens) {
            totalItens = totalItens.add(item.calcularTotal());
        }

        this.total = totalItens;
        this.totalFinal = totalItens;
    }

}
