package com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Item;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import jakarta.persistence.EntityManager;
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

    public Item toModel(final EntityManager entityManager) {
        final Livro livro = entityManager.find(Livro.class, livroId);

        return Item.criar(quantidade, livro);
    }

}
