package com.rosivaldolucas.desafiocdcdeveficiente.autor.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.autor.Autor;

public record BuscarDetalheSiteAutorOutput(
        Long id,
        String nome,
        String email,
        String descricao
) {

  public static BuscarDetalheSiteAutorOutput criar(final Autor autor) {
    return new BuscarDetalheSiteAutorOutput(autor.getId(), autor.getNome(), autor.getEmail(), autor.getDescricao());
  }

}
