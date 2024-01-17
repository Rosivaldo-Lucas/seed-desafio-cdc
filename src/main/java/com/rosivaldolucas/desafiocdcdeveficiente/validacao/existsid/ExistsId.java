package com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { ExistsIdValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

  String message() default "{com.rosivaldolucas.beanvalidation.existsid}";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  Class<?> domainClass();

  String fieldName();

}
