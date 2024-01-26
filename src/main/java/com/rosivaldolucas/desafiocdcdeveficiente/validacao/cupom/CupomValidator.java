package com.rosivaldolucas.desafiocdcdeveficiente.validacao.cupom;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CupomValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(@NonNull final Class<?> clazz) {
        return NovaCompraInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull final Object target, @NonNull final Errors errors) {
        if (errors.hasErrors()) return;

        final NovaCompraInput input = (NovaCompraInput) target;

        final Cupom cupom = this.entityManager.find(Cupom.class, input.codigoCupom());

        if (cupom == null) {
            errors.rejectValue("cupom", null, "cupom não encontrado.");

            return;
        }

        if (!cupom.dataValidadeValida()) {
            errors.rejectValue("cupom", null, "cupom expirado.");
        }
    }

}
