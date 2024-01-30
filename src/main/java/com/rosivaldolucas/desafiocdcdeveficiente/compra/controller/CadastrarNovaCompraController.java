package com.rosivaldolucas.desafiocdcdeveficiente.compra.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.service.CadastrarNovaCompraService;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.cupom.CupomValidator;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.documentoCpfCnpj.DocumentoCpfCnpjValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CadastrarNovaCompraController {

    private final DocumentoCpfCnpjValidator documentoCpfCnpjValidator;
    private final CupomValidator cupomValidator;

    private final CadastrarNovaCompraService cadastrarNovaCompraService;

    public CadastrarNovaCompraController(final DocumentoCpfCnpjValidator documentoCpfCnpjValidator, final CupomValidator cupomValidator, final CadastrarNovaCompraService cadastrarNovaCompraService) {
        this.documentoCpfCnpjValidator = documentoCpfCnpjValidator;
        this.cupomValidator = cupomValidator;
        this.cadastrarNovaCompraService = cadastrarNovaCompraService;
    }

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(this.documentoCpfCnpjValidator, this.cupomValidator);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid final NovaCompraInput input) {
        this.cadastrarNovaCompraService.cadastrar(input);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
