package com.rosivaldolucas.desafiocdcdeveficiente.livro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class BuscarDetalheLivroController {

  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @GetMapping("/{livroId}")
  public ResponseEntity<BuscarDetalheLivroOutput> buscarDetalhe(@PathVariable Long livroId) {
    final Livro livroCadastrado = this.entityManager.find(Livro.class, livroId);

    if (livroCadastrado == null) {
      throw new RuntimeException("livro de 'id' " + livroId + " não encontrado");
    }

    final BuscarDetalheLivroOutput output = BuscarDetalheLivroOutput.criar(livroCadastrado);

    return ResponseEntity.status(HttpStatus.OK).body(output);
  }

}
