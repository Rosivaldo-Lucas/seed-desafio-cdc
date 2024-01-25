package com.rosivaldolucas.desafiocdcdeveficiente.validacao.documentoCpfCnpj;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.NovaCompraInput;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DocumentoCpfCnpjValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return NovaCompraInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        if (errors.hasErrors()) return;

        final NovaCompraInput input = (NovaCompraInput) target;

        if (!input.documentoValido()) {
            errors.rejectValue("documento", null, "documento precisa ser cpf ou cnpj.");
        }
    }
}
