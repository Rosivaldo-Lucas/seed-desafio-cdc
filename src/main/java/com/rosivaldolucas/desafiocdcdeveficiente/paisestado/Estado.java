package com.rosivaldolucas.desafiocdcdeveficiente.paisestado;

import jakarta.persistence.*;

@Entity
@Table(name = "ESTADO")
public class Estado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NOME")
  private String nome;

  @ManyToOne
  @JoinColumn(name = "ID_PAIS")
  private Pais pais;

  protected Estado() { }

  private Estado(final String nome, final Pais pais) {
    this.nome = nome;
    this.pais = pais;
  }

  public static Estado criar(final String nome, final Pais pais) {
    return new Estado(nome, pais);
  }

  public boolean pertenceAPais(final Pais pais) {
    return this.pais.equals(pais);
  }

}
