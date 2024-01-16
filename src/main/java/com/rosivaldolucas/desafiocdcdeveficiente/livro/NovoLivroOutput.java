package com.rosivaldolucas.desafiocdcdeveficiente.livro;

public record NovoLivroOutput(
        Long id
) {

  public static NovoLivroOutput crair(final Livro livro) {
    return new NovoLivroOutput(livro.getId());
  }

}
