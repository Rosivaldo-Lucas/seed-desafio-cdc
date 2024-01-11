package com.rosivaldolucas.desafiocdcdeveficiente;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

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

    this.validar();
  }

  private void validar() {
    if (!StringUtils.hasText(this.nome)) throw new IllegalArgumentException("nome é obrigatório.");
    if (!StringUtils.hasText(this.email)) throw new IllegalArgumentException("email é obrigatório.");
    if (!StringUtils.hasText(this.descricao)) throw new IllegalArgumentException("descricao é obrigatório.");
    if (this.criadoEm == null) throw new IllegalArgumentException("criado em é obrigatório");

    if (this.descricao.length() > 400) throw new IllegalArgumentException("descrição deve ser menor ou igual a 400 caracteres.");
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
