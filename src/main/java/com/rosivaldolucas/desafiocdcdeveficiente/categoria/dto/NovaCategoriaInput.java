package com.rosivaldolucas.desafiocdcdeveficiente.categoria.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.categoria.Categoria;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.uniquevalue.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NovaCategoriaInput(
        @NotBlank
        @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
        String nome
) {

  public Categoria toModel() {
    return Categoria.criar(nome);
  }

}
