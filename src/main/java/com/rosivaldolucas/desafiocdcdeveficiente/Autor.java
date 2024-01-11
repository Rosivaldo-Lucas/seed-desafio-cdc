package com.rosivaldolucas.desafiocdcdeveficiente;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUTOR")
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String descricao;
  private LocalDateTime criadoEm;

  protected Autor() { }

  public Autor(final String nome, final String email, final String descricao) {
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
    this.criadoEm = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getDescricao() {
    return descricao;
  }

  public LocalDateTime getCriadoEm() {
    return criadoEm;
  }

}
