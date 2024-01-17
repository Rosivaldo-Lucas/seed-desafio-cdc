package com.rosivaldolucas.desafiocdcdeveficiente.livro.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.autor.dto.BuscarDetalheSiteAutorOutput;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BuscarDetalheLivroOutput(
        Long id,
        String isbn,
        String titulo,
        String resumo,
        String sumario,
        BigDecimal preco,
        Integer numeroPaginas,
        LocalDate dataPublicacao,
        BuscarDetalheSiteAutorOutput autor
) {

  public static BuscarDetalheLivroOutput criar(final Livro livro) {
    final BuscarDetalheSiteAutorOutput autorOutput = BuscarDetalheSiteAutorOutput.criar(livro.getAutor());

    return new BuscarDetalheLivroOutput(
            livro.getId(),
            livro.getIsbn(),
            livro.getTitulo(),
            livro.getResumo(),
            livro.getSumario(),
            livro.getPreco(),
            livro.getNumeroPaginas(),
            livro.getDataPublicacao(),
            autorOutput
    );
  }

}
