package com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid;

import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

  private Class<?> domainClazz;
  private String domainAttribute;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void initialize(final ExistsId constraintAnnotation) {
    this.domainClazz = constraintAnnotation.domainClass();
    this.domainAttribute = constraintAnnotation.fieldName();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    final Query query = this.entityManager.createQuery("SELECT CASE WHEN EXISTS (" +
            "   SELECT 1 FROM " + this.domainClazz.getName() + "   WHERE " + this.domainAttribute + " = :value" +
            ") THEN true ELSE false END"
    );

    query.setParameter("value", value);

    final Boolean result = (Boolean) query.getSingleResult();

    Assert.state(result, "não foi encontrada " + this.domainClazz + " com o atributo " + this.domainAttribute + " = " + value);

    return (boolean) query.getSingleResult();
  }

}
