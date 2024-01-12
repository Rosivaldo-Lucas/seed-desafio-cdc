package com.rosivaldolucas.desafiocdcdeveficiente.validacao;

import com.rosivaldolucas.desafiocdcdeveficiente.validacao.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  private Class<?> domainClazz;
  private String domainAttribute;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void initialize(final UniqueValue constraintAnnotation) {
    this.domainClazz = constraintAnnotation.domainClass();
    this.domainAttribute = constraintAnnotation.fieldName();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    final Query query = this.entityManager.createQuery("SELECT 1 FROM " + this.domainClazz.getName() + " WHERE " + this.domainAttribute + "=:value");
    query.setParameter("value", value);

    final List<?> list = query.getResultList();

    Assert.state(list.size() <= 1, "foi encontrado mais de um " + this.domainClazz + " com o atributo " + this.domainAttribute + " = " + value);

    return list.isEmpty();
  }

}
