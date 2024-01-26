package com.rosivaldolucas.desafiocdcdeveficiente.cupom;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "CUPOM")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private Integer percentualDesconto;

    private LocalDate dataValidade;

    protected Cupom() { }

    private Cupom(final Long id, final String codigo, final Integer percentualDesconto, final LocalDate dataValidade) {
        this.id = id;
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataValidade = dataValidade;
    }

    public static Cupom criar(final String codigo, final Integer percentualDesconto, final LocalDate dataValidade) {
        return new Cupom(null, codigo, percentualDesconto, dataValidade);
    }

}
