package com.rosivaldolucas.desafiocdcdeveficiente;

import java.time.LocalDateTime;

public record NovoAutorOutput(
        String id,
        String nome,
        String email,
        String descricao,
        LocalDateTime criadoEm
) {

}
