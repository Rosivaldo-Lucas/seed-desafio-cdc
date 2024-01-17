package com.rosivaldolucas.desafiocdcdeveficiente.livro;

import com.rosivaldolucas.desafiocdcdeveficiente.validacao.ExistsId;
import jakarta.validation.constraints.NotNull;

public record LivroIdInput(
        @NotNull
        @ExistsId(domainClass = Livro.class, fieldName = "id")
        Long livroId
) {
}
