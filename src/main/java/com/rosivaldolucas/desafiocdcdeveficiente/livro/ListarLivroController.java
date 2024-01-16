package com.rosivaldolucas.desafiocdcdeveficiente.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class ListarLivroController {

  private final LivroRepository livroRepository;

  public ListarLivroController(final LivroRepository livroRepository) {
    this.livroRepository = livroRepository;
  }

  @GetMapping
  public ResponseEntity<Page<ListarLivroOutput>> listar(final Pageable pageable) {
    final Page<Livro> listaLivroPaginada = this.livroRepository.findAll(pageable);

    final Page<ListarLivroOutput> output = ListarLivroOutput.criar(listaLivroPaginada, pageable);

    return ResponseEntity.status(HttpStatus.OK).body(output);
  }

}
