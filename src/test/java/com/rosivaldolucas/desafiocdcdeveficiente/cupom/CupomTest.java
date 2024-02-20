package com.rosivaldolucas.desafiocdcdeveficiente.cupom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CupomTest {

  @ParameterizedTest
  @CsvSource({ "0, true", "1, true" })
  public void t01(final long valor, final boolean resultado) {
    final Cupom cupom = Cupom.criar("cupom", BigDecimal.TEN, LocalDate.now().plusDays(valor));

    Assertions.assertEquals(resultado, cupom.dataValidadeValida());
  }

  @DisplayName("cupom com data passada nao eh mais valido")
  @Test
  public void t02() {
    final Cupom cupom = Cupom.criar("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1L));

    ReflectionTestUtils.setField(cupom, "dataValidade", LocalDate.now().minusDays(1L));

    Assertions.assertFalse(cupom.dataValidadeValida());
  }

  @DisplayName("nao pode criar cupom com data no passado")
  @Test
  public void t03() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> Cupom.criar("codido", BigDecimal.TEN, LocalDate.now().minusDays(1L))
    );
  }

}
