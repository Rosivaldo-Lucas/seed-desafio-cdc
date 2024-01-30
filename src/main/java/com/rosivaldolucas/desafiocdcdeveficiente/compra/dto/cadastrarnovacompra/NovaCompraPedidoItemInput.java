package com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra;

import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NovaCompraPedidoItemInput(
        @NotNull
        @Positive
        Integer quantidade,
        @NotNull
        @ExistsId(domainClass = Livro.class, fieldName = "id")
        Long livroId
) {

}
