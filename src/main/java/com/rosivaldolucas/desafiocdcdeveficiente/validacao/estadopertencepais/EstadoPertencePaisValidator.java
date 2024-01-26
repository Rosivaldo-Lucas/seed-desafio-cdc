package com.rosivaldolucas.desafiocdcdeveficiente.validacao.estadopertencepais;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertencePaisValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(@NonNull final Class<?> clazz) {
        return EstadoPertencePaisValidator.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull final Object target, final Errors errors) {
        if (errors.hasErrors()) return;

        final NovaCompraInput input = (NovaCompraInput) target;

        final Pais pais = this.entityManager.find(Pais.class, input.paisId());
        final Estado estado = this.entityManager.find(Estado.class, input.estadoId());

        if (!estado.pertenceAPais(pais)) {
            errors.rejectValue("estadoId", null, "estado não pertence ao pais informado.");
        }
    }

}
