package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

import jakarta.validation.constraints.NotBlank;

public record NovaCategoriaInput(
        @NotBlank String nome
) {

  public Categoria toModel() {
    return Categoria.criar(nome);
  }

}
