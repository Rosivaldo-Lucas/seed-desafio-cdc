package com.rosivaldolucas.desafiocdcdeveficiente.paisestado.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.dto.NovoEstadoInput;
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
@RequestMapping("/estados")
public class CadastrarNovoEstadoController {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @PostMapping
  public ResponseEntity<Void> cadastrar(@RequestBody @Valid final NovoEstadoInput input) {
    final Estado novoEstado = input.toModel(this.entityManager);

    this.entityManager.persist(novoEstado);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
