package com.rosivaldolucas.desafiocdcdeveficiente.cupom;

import jakarta.persistence.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CUPOM")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private BigDecimal percentualDesconto;

    private LocalDate dataValidade;

    protected Cupom() { }

    private Cupom(final Long id, final String codigo, final BigDecimal percentualDesconto, final LocalDate dataValidade) {
        this.dataValidade = dataValidade;

        Assert.isTrue(this.dataValidadeValida(), "a validade tem que ser no futuro");

        this.id = id;
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
    }

    public static Cupom criar(final String codigo, final BigDecimal percentualDesconto, final LocalDate dataValidade) {
        return new Cupom(null, codigo, percentualDesconto, dataValidade);
    }

    public boolean dataValidadeValida() {
        return this.dataValidade.isAfter(LocalDate.now()) || dataValidade.equals(LocalDate.now());
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

}
