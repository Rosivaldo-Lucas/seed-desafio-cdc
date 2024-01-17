package com.rosivaldolucas.desafiocdcdeveficiente.livro.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import jakarta.validation.constraints.NotNull;

public record LivroIdInput(
        @NotNull
        @ExistsId(domainClass = Livro.class, fieldName = "id")
        Long livroId
) {
}
