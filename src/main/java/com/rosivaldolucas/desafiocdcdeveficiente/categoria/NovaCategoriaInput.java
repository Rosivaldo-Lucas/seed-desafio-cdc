package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

import com.rosivaldolucas.desafiocdcdeveficiente.validacao.UniqueValue;
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
