package com.rosivaldolucas.desafiocdcdeveficiente.compra.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.repository.CupomRepository;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.cupom.CupomValidator;
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

    private final DocumentoCpfCnpjValidator documentoCpfCnpjValidator;
    private final CupomValidator cupomValidator;

    private final CupomRepository cupomRepository;

    public CadastrarNovaCompraController(final DocumentoCpfCnpjValidator documentoCpfCnpjValidator, final CupomValidator cupomValidator, final CupomRepository cupomRepository) {
        this.documentoCpfCnpjValidator = documentoCpfCnpjValidator;
        this.cupomValidator = cupomValidator;
        this.cupomRepository = cupomRepository;
    }

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(this.documentoCpfCnpjValidator, this.cupomValidator);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid final NovaCompraInput input) {
        final Compra novaCompra = input.toModel(this.entityManager, this.cupomRepository);

        entityManager.persist(novaCompra);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
