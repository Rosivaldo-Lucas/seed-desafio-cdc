package com.rosivaldolucas.desafiocdcdeveficiente.compra;

import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Embeddable
public class Item {

    private Integer quantidade;

    private BigDecimal precoMomento;

    @ManyToOne
    @JoinColumn(name = "ID_LIVRO")
    private Livro livro;

    protected Item() { }

    private Item(final Integer quantidade, final Livro livro) {
        this.quantidade = quantidade;
        this.livro = livro;
        this.precoMomento = this.livro.getPreco();
    }

    public static Item criar(final Integer quantidade, final Livro livro) {
        return new Item(quantidade, livro);
    }

    public BigDecimal calcularTotal() {
        return this.precoMomento.multiply(BigDecimal.valueOf(quantidade));
    }

}
