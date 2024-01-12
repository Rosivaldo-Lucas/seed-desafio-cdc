package com.rosivaldolucas.desafiocdcdeveficiente.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

  Optional<Categoria> findByNome(final String nome);

}
