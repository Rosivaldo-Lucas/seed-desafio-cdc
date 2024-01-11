package com.rosivaldolucas.desafiocdcdeveficiente;

import java.time.LocalDateTime;

public record NovoAutorOutput(
        Long id,
        String nome,
        String email,
        String descricao,
        LocalDateTime criadoEm
) {

  public static NovoAutorOutput criar(final Autor autor) {
    return new NovoAutorOutput(
            autor.getId(),
            autor.getNome(),
            autor.getEmail(),
            autor.getDescricao(),
            autor.getCriadoEm()
    );
  }

}
