package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CadastrarNovaCategoriaController {

  @PersistenceContext
  private EntityManager entityManager;

  private final ProibeNomeDuplicadoCategoriaValidator proibeNomeDuplicadoCategoriaValidator;

  public CadastrarNovaCategoriaController(final ProibeNomeDuplicadoCategoriaValidator proibeNomeDuplicadoCategoriaValidator) {
    this.proibeNomeDuplicadoCategoriaValidator = proibeNomeDuplicadoCategoriaValidator;
  }

  @InitBinder
  public void initBinder(final WebDataBinder webDataBinder) {
    webDataBinder.addValidators(this.proibeNomeDuplicadoCategoriaValidator);
  }

  @Transactional
  @PostMapping
  public ResponseEntity<NovaCategoriaOutput> cadastrar(@RequestBody @Valid final NovaCategoriaInput input) {
    final Categoria novaCategoria = input.toModel();

    this.entityManager.persist(novaCategoria);

    final NovaCategoriaOutput output = NovaCategoriaOutput.criar(novaCategoria);

    return ResponseEntity.status(HttpStatus.CREATED).body(output);
  }

}
