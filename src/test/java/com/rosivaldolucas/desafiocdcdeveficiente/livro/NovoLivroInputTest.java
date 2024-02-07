package com.rosivaldolucas.desafiocdcdeveficiente.livro;

import com.rosivaldolucas.desafiocdcdeveficiente.autor.Autor;
import com.rosivaldolucas.desafiocdcdeveficiente.categoria.Categoria;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.dto.NovoLivroInput;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class NovoLivroInputTest {

  private final NovoLivroInput input = new NovoLivroInput(
          "Teste", "Resumo...", "Sumário...", BigDecimal.valueOf(100.00D), 150,
          "123456", LocalDate.of(2024, Month.MARCH, 2), 1L, 1L
  );

  @DisplayName("deve criar um novo livro quando categoria e autor estao cadastrados")
  @Test
  public void t01() {
    final EntityManager entityManager = Mockito.mock(EntityManager.class);

    Mockito.when(entityManager.find(Categoria.class, 1L)).thenReturn(Categoria.criar("Teste"));
    Mockito.when(entityManager.find(Autor.class, 1L)).thenReturn(Autor.criar("Teste", "teste@gmail.com", "Teste..."));

    Assertions.assertNotNull(input.toModel(entityManager));

    entityManager.close();
  }

  @DisplayName("nao deve criar um novo livro quando autor nao existe no banco de dados")
  @Test
  public void t02() {
    final EntityManager entityManager = Mockito.mock(EntityManager.class);

    Mockito.when(entityManager.find(Categoria.class, 1L)).thenReturn(Categoria.criar("Teste"));
    Mockito.when(entityManager.find(Autor.class, 1L)).thenReturn(null);

    final IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> input.toModel(entityManager));

    Assertions.assertEquals(exception.getMessage(), "livro não pode ser cadastrado sem autor");

    entityManager.close();
  }

  @DisplayName("nao deve criar um novo livro quando categoria nao existe no banco de dados")
  @Test
  public void t03() {
    final EntityManager entityManager = Mockito.mock(EntityManager.class);

    Mockito.when(entityManager.find(Categoria.class, 1L)).thenReturn(null);
    Mockito.when(entityManager.find(Autor.class, 1L)).thenReturn(Autor.criar("Teste", "teste@gmail.com", "Teste..."));

    final IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> input.toModel(entityManager));

    Assertions.assertEquals(exception.getMessage(), "livro não pode ser cadastrado sem categoria");

    entityManager.close();
  }

}
