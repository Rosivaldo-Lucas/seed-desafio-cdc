package com.rosivaldolucas.desafiocdcdeveficiente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

  Optional<Autor> findByEmail(final String email);

}
