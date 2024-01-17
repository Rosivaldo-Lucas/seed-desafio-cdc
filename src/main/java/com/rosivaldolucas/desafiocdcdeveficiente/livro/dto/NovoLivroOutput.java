package com.rosivaldolucas.desafiocdcdeveficiente.livro.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;

public record NovoLivroOutput(
        Long id
) {

  public static NovoLivroOutput crair(final Livro livro) {
    return new NovoLivroOutput(livro.getId());
  }

}
