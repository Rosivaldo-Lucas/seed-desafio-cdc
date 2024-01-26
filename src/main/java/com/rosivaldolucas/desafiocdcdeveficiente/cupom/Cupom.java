package com.rosivaldolucas.desafiocdcdeveficiente.cupom;

import jakarta.persistence.*;

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
        this.id = id;
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataValidade = dataValidade;
    }

    public static Cupom criar(final String codigo, final BigDecimal percentualDesconto, final LocalDate dataValidade) {
        return new Cupom(null, codigo, percentualDesconto, dataValidade);
    }

    public boolean dataValidadeValida() {
        return this.dataValidade.isAfter(LocalDate.now());
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

}
