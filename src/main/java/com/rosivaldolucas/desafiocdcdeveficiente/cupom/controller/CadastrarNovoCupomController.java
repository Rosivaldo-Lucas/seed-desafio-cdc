package com.rosivaldolucas.desafiocdcdeveficiente.cupom.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.dto.NovoCupomInput;
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
@RequestMapping("/cupons")
public class CadastrarNovoCupomController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid final NovoCupomInput input) {
        final Cupom novoCupom = input.toModel();

        this.entityManager.persist(novoCupom);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
