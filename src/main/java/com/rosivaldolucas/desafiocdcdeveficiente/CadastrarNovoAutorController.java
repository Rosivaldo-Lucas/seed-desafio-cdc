package com.rosivaldolucas.desafiocdcdeveficiente;

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
@RequestMapping("/autores")
public class CadastrarNovoAutorController {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @PostMapping
  public ResponseEntity<NovoAutorOutput> cadastrar(@RequestBody @Valid final NovoAutorInput novoAutorInput) {
    final Autor novoAutor = novoAutorInput.toModel();

    this.entityManager.persist(novoAutor);

    final NovoAutorOutput novoAutorOutput = NovoAutorOutput.criar(novoAutor);

    return ResponseEntity.status(HttpStatus.CREATED).body(novoAutorOutput);
  }

}
