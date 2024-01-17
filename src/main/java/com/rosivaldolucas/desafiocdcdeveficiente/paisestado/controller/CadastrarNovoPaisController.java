package com.rosivaldolucas.desafiocdcdeveficiente.paisestado.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.dto.NovoPaisInput;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
public class CadastrarNovoPaisController {

  @PersistenceContext
  private EntityManager entityManager;

  @PostMapping
  public ResponseEntity<Void> cadastrar(@RequestBody @Valid final NovoPaisInput input) {
    final Pais novoPais = input.toModel();

    this.entityManager.persist(novoPais);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
