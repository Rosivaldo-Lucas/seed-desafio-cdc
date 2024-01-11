package com.rosivaldolucas.desafiocdcdeveficiente;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class CadastrarNovoAutorController {

  @PostMapping
  public ResponseEntity<NovoAutorOutput> cadastrar(@RequestBody @Valid final NovoAutorInput novoAutorInput) {
    final Autor novoAutor = new Autor(novoAutorInput.nome(), novoAutorInput.email(), novoAutorInput.descricao());

    final NovoAutorOutput novoAutorOutput = new NovoAutorOutput(
            novoAutor.getId(),
            novoAutor.getNome(),
            novoAutor.getEmail(),
            novoAutor.getDescricao(),
            novoAutor.getCriadoEm()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(novoAutorOutput);
  }

}
