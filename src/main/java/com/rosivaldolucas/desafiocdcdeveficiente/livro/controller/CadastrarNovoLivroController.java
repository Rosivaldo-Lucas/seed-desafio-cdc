package com.rosivaldolucas.desafiocdcdeveficiente.livro.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.dto.NovoLivroInput;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.dto.NovoLivroOutput;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class CadastrarNovoLivroController {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @PostMapping
  public ResponseEntity<NovoLivroOutput> cadastrar(@RequestBody @Valid final NovoLivroInput input) {
    final Livro novoLivro = input.toModel(this.entityManager);

    this.entityManager.persist(novoLivro);

    final NovoLivroOutput output = NovoLivroOutput.crair(novoLivro);

    return ResponseEntity.status(HttpStatus.CREATED).body(output);
  }

}
