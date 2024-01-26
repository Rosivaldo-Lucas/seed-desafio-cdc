package com.rosivaldolucas.desafiocdcdeveficiente.cupom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.uniquevalue.UniqueValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record NovoCupomInput(
        @NotBlank
        @UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
        String codigo,
        @NotNull
        @Positive
        Integer percentualDesconto,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate dataValidade
) {

    public Cupom toModel() {
        return Cupom.criar(this.codigo, this.percentualDesconto, this.dataValidade);
    }

}
