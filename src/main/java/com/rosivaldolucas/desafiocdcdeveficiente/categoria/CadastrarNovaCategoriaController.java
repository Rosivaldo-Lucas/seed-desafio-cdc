package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

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
@RequestMapping("/categorias")
public class CadastrarNovaCategoriaController {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @PostMapping
  public ResponseEntity<NovaCategoriaOutput> cadastrar(@RequestBody @Valid final NovaCategoriaInput input) {
    final Categoria novaCategoria = input.toModel();

    this.entityManager.persist(novaCategoria);

    final NovaCategoriaOutput output = NovaCategoriaOutput.criar(novaCategoria);

    return ResponseEntity.status(HttpStatus.CREATED).body(output);
  }

}
