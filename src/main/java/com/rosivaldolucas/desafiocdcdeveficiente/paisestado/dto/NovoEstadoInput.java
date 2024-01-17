package com.rosivaldolucas.desafiocdcdeveficiente.paisestado.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.uniquevalue.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovoEstadoInput(
        @NotBlank
        @UniqueValue(domainClass = Estado.class, fieldName = "nome")
        String nome,

        @NotNull
        @ExistsId(domainClass = Pais.class, fieldName = "id")
        Long paisId
) {

  public Estado toModel(final EntityManager entityManager) {
    final Pais pais = entityManager.find(Pais.class, paisId);

    return Estado.criar(nome, pais);
  }

}
