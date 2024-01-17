package com.rosivaldolucas.desafiocdcdeveficiente.categoria.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.categoria.Categoria;

public record NovaCategoriaOutput(
        Long id,
        String nome
) {

  public static NovaCategoriaOutput criar(final Categoria categoria) {
    return new NovaCategoriaOutput(categoria.getId(), categoria.getNome());
  }

}
