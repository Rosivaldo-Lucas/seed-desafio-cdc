package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  protected Categoria() { }

  private Categoria(final String nome) {
    this.nome = nome;
  }

  public static Categoria criar(final String nome) {
    return new Categoria(nome);
  }

  private void validar() {
    if (!StringUtils.hasText(this.nome)) throw new IllegalArgumentException("nome é obrigatório.");
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

}
