package com.rosivaldolucas.desafiocdcdeveficiente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovoAutorInput(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Size(max = 400) String descricao
) {

}
