package com.rosivaldolucas.desafiocdcdeveficiente.cupom.repository;

import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    Optional<Cupom> findByCodigo(final String codigo);

}
