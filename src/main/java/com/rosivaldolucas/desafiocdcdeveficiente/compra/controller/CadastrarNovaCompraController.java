package com.rosivaldolucas.desafiocdcdeveficiente.compra.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.documentoCpfCnpj.DocumentoCpfCnpjValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CadastrarNovaCompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new DocumentoCpfCnpjValidator());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid final NovaCompraInput input) {
        final Compra novaCompra = input.toModel(this.entityManager);

        entityManager.persist(novaCompra);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
