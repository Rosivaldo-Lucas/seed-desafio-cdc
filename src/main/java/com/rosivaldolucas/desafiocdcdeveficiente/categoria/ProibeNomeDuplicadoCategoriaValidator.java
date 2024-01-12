package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {

  private final CategoriaRepository categoriaRepository;

  public ProibeNomeDuplicadoCategoriaValidator(final CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public boolean supports(final Class<?> clazz) {
    return NovaCategoriaInput.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    final NovaCategoriaInput novaCategoriaInput = (NovaCategoriaInput) target;

    final Optional<Categoria> categoriaOptional = this.categoriaRepository.findByNome(novaCategoriaInput.nome());

    if (categoriaOptional.isPresent()) {
      errors.rejectValue("nome", null, "categoria de 'nome' " + novaCategoriaInput.nome() + " já cadastrada.");
    }
  }

}
