package com.rosivaldolucas.desafiocdcdeveficiente.compra;

import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

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

    public void aplicarCupom(final Cupom cupom) {

    }

    public BigDecimal obterTotal() {
        return this.total;
    }

    private void calcularTotalItens() {
        BigDecimal totalItens = BigDecimal.ZERO;

        for (final Item item : this.itens) {
            totalItens = totalItens.add(item.calcularTotal());
        }

        this.total = totalItens;
    }

}
