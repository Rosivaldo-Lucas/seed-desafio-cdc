package com.rosivaldolucas.desafiocdcdeveficiente.livro.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public record ListarLivroOutput(
        Long id,
        String titulo,
        BigDecimal preco
) {

  public static Page<ListarLivroOutput> criar(final Page<Livro> livros, final Pageable pageable) {
    final List<ListarLivroOutput> list = livros.stream()
            .map(livro -> new ListarLivroOutput(livro.getId(), livro.getTitulo(), livro.getPreco()))
            .toList();

    return new PageImpl<>(list, pageable, list.size());
  }

}
