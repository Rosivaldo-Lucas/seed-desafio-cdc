package com.rosivaldolucas.desafiocdcdeveficiente.livro;

import com.rosivaldolucas.desafiocdcdeveficiente.autor.Autor;
import com.rosivaldolucas.desafiocdcdeveficiente.categoria.Categoria;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LIVRO")
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String isbn;

  private String titulo;

  private String resumo;

  private String sumario;

  private BigDecimal preco;

  private Integer numeroPaginas;

  private LocalDate dataPublicacao;

  @ManyToOne
  @JoinColumn(name = "ID_AUTOR")
  private Autor autor;

  @ManyToOne
  @JoinColumn(name = "ID_CATEGORIA")
  private Categoria categoria;

  protected Livro() { }

  private Livro(final Long id, final String isbn, final String titulo, final String resumo, final String sumario, final BigDecimal preco, final Integer numeroPaginas, final LocalDate dataPublicacao, final Autor autor, final Categoria categoria) {
    this.id = id;
    this.isbn = isbn;
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.preco = preco;
    this.numeroPaginas = numeroPaginas;
    this.dataPublicacao = dataPublicacao;
    this.autor = autor;
    this.categoria = categoria;
  }

  public static Livro criar(final String isbn, final String titulo, final String resumo, final String sumario, final BigDecimal preco, final Integer numeroPaginas, final LocalDate dataPublicacao, final Autor autor, final Categoria categoria) {
    return new Livro(null, isbn, titulo, resumo, sumario, preco, numeroPaginas, dataPublicacao, autor, categoria);
  }

  public Long getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public BigDecimal getPreco() {
    return preco;
  }

}
