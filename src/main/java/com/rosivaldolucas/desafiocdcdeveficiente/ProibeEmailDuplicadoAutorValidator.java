package com.rosivaldolucas.desafiocdcdeveficiente;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

  private final AutorRepository autorRepository;

  public ProibeEmailDuplicadoAutorValidator(final AutorRepository autorRepository) {
    this.autorRepository = autorRepository;
  }

  @Override
  public boolean supports(final Class<?> clazz) {
    return NovoAutorInput.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    final NovoAutorInput novoAutorInput = (NovoAutorInput) target;

    final Optional<Autor> autorOptional = this.autorRepository.findByEmail(novoAutorInput.email());

    if (autorOptional.isPresent()) {
      errors.rejectValue("email", null, "autor de 'email' " + novoAutorInput.email() + " já cadastrado.");
    }
  }

}
