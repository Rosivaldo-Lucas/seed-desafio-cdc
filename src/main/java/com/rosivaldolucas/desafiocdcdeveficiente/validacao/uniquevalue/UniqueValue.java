package com.rosivaldolucas.desafiocdcdeveficiente.validacao.uniquevalue;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

  String message() default "{com.rosivaldolucas.beanvalidation.uniquevalue}";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  Class<?> domainClass();

  String fieldName();

}
