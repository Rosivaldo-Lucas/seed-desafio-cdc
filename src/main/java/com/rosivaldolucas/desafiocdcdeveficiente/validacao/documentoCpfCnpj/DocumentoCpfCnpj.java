package com.rosivaldolucas.desafiocdcdeveficiente.validacao.documentoCpfCnpj;

import jakarta.validation.Payload;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@ConstraintComposition(CompositionType.OR)
@Retention(RetentionPolicy.RUNTIME)
@CPF
@CNPJ
public @interface DocumentoCpfCnpj {

    String message() default "{com.rosivaldolucas.beanvalidation.documentoCpfCnpj}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
