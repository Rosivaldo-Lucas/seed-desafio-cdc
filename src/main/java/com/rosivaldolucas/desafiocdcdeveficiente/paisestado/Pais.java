package com.rosivaldolucas.desafiocdcdeveficiente.paisestado;

import jakarta.persistence.*;

@Entity
@Table(name = "PAIS")
public class Pais {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NOME")
  private String nome;

  protected Pais() { }

  private Pais(final String nome) {
    this.nome = nome;
  }

  public static Pais criar(final String nome) {
    return new Pais(nome);
  }

}
