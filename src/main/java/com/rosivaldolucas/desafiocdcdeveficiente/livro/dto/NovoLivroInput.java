package com.rosivaldolucas.desafiocdcdeveficiente.livro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosivaldolucas.desafiocdcdeveficiente.autor.Autor;
import com.rosivaldolucas.desafiocdcdeveficiente.categoria.Categoria;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.uniquevalue.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NovoLivroInput(
        @NotBlank
        @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
        String titulo,

        @NotBlank
        @Size(max = 500)
        String resumo,

        @NotBlank
        String sumario,

        @NotNull
        @Min(20)
        BigDecimal preco,

        @NotNull
        @Min(100)
        Integer numeroPaginas,

        @NotBlank
        @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
        String isbn,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate dataPublicacao,

        @NotNull
        @ExistsId(domainClass = Autor.class, fieldName = "id")
        Long autorId,

        @NotNull
        @ExistsId(domainClass = Categoria.class, fieldName = "id")
        Long categoriaId
) {

  public Livro toModel(final EntityManager entityManager) {
    final Autor autor = entityManager.find(Autor.class, autorId);
    final Categoria categoria = entityManager.find(Categoria.class, categoriaId);

    return Livro.criar(isbn, titulo, resumo, sumario, preco, numeroPaginas, dataPublicacao, autor, categoria);
  }

}
