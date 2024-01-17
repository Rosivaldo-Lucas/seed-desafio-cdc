package com.rosivaldolucas.desafiocdcdeveficiente.paisestado.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.uniquevalue.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NovoPaisInput(
        @NotBlank
        @UniqueValue(domainClass = Pais.class, fieldName = "nome")
        String nome
) {

  public Pais toModel() {
    return Pais.criar(nome);
  }

}
