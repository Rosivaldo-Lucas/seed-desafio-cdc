package com.rosivaldolucas.desafiocdcdeveficiente.compra;

import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    private BigDecimal percentualDescontoMomento;
    private LocalDate dataValidadeMomento;

    @ManyToOne
    private Cupom cupom;

    protected CupomAplicado() { }

    private CupomAplicado(final Cupom cupom) {
        this.cupom = cupom;

        this.percentualDescontoMomento = this.cupom.getPercentualDesconto();
        this.dataValidadeMomento = this.cupom.getDataValidade();
    }

    public static CupomAplicado criar(final Cupom cupom) {
        return new CupomAplicado(cupom);
    }

    public BigDecimal getPercentualDescontoMomento() {
        return percentualDescontoMomento;
    }

    public LocalDate getDataValidadeMomento() {
        return dataValidadeMomento;
    }

}
