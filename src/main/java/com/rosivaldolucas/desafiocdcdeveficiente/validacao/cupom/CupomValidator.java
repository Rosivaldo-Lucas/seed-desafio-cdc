package com.rosivaldolucas.desafiocdcdeveficiente.validacao.cupom;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.repository.CupomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CupomValidator implements Validator {

    private final CupomRepository cupomRepository;

    public CupomValidator(final CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    @Override
    public boolean supports(@NonNull final Class<?> clazz) {
        return NovaCompraInput.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull final Object target, @NonNull final Errors errors) {
        if (errors.hasErrors()) return;

        final NovaCompraInput input = (NovaCompraInput) target;

        final Optional<Cupom> cupomOptional = this.cupomRepository.findByCodigo(input.codigoCupom());

        if (cupomOptional.isEmpty()) {
            errors.rejectValue("cupom", null, "cupom não encontrado.");

            return;
        }

        if (!cupomOptional.get().dataValidadeValida()) {
            errors.rejectValue("cupom", null, "cupom expirado.");
        }
    }

}
