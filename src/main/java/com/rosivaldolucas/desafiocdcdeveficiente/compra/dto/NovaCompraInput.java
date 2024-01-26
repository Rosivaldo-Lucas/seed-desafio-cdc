package com.rosivaldolucas.desafiocdcdeveficiente.compra.dto;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.Pedido;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import com.rosivaldolucas.desafiocdcdeveficiente.validacao.existsid.ExistsId;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.StringUtils;

import java.util.function.Function;

public record NovaCompraInput(
        @NotBlank
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String documento,
        @NotBlank
        String endereco,
        @NotBlank
        String complemento,
        @NotBlank
        String cidade,
        @NotBlank
        String cep,
        @NotBlank
        String telefone,
        @ExistsId(domainClass = Pais.class, fieldName = "id")
        @NotNull
        Long paisId,
        @ExistsId(domainClass = Estado.class, fieldName = "id")
        Long estadoId,
        @NotNull
        @Valid
        NovaCompraPedidoInput pedido,
        String codigoCupom
) {

    public Compra toModel(final EntityManager entityManager) {
        final Pais pais = entityManager.find(Pais.class, this.paisId);

        final Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(entityManager);

        final Compra novaCompra = Compra.criar(this.nome, this.sobrenome, this.email, this.documento, this.endereco, this.complemento, this.cidade, this.cep, this.telefone, pais, funcaoCriacaoPedido);

        if (this.estadoId != null) {
            final Estado estado = entityManager.find(Estado.class, this.estadoId);

            novaCompra.adicionarEstado(estado);
        }

        if (StringUtils.hasText(this.codigoCupom)) {
            final Cupom cupom = entityManager.find(Cupom.class, this.codigoCupom);

            novaCompra.aplicarCupom(cupom);
        }

        return novaCompra;
    }

    public boolean documentoValido() {
        if (!StringUtils.hasText(this.documento)) throw new IllegalArgumentException("documento não pode ser nulo ou vazio.");

        final CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        final CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(this.documento, null) || cnpjValidator.isValid(this.documento, null);
    }

}
