package com.rosivaldolucas.desafiocdcdeveficiente.compra.controller;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.detalharcompra.DetalheCompraOutput;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class DetalheCompraController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{compraId}")
    public ResponseEntity<DetalheCompraOutput> buscarDetalhe(@PathVariable final Long compraId) {
        final Compra compraCadastrada = this.entityManager.find(Compra.class, compraId);

        final DetalheCompraOutput output = DetalheCompraOutput.criar(compraCadastrada);

        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

}
