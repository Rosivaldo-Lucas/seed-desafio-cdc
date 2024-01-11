package com.rosivaldolucas.desafiocdcdeveficiente;

import java.time.LocalDateTime;
import java.util.UUID;

public class Autor {

  private String id;
  private String nome;
  private String email;
  private String descricao;
  private LocalDateTime criadoEm;

  public Autor(final String nome, final String email, final String descricao) {
    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
    this.criadoEm = LocalDateTime.now();
  }

  public String getId() {
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
