package com.rosivaldolucas.desafiocdcdeveficiente.compra.service;

import com.rosivaldolucas.desafiocdcdeveficiente.compra.Compra;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.Item;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.Pedido;
import com.rosivaldolucas.desafiocdcdeveficiente.compra.dto.cadastrarnovacompra.NovaCompraInput;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.Cupom;
import com.rosivaldolucas.desafiocdcdeveficiente.cupom.repository.CupomRepository;
import com.rosivaldolucas.desafiocdcdeveficiente.livro.Livro;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Function;

@Service
public class CadastrarNovaCompraService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CupomRepository cupomRepository;

    public CadastrarNovaCompraService(final CupomRepository cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    @Transactional
    public void cadastrar(final NovaCompraInput input) {
        final Pais pais = this.entityManager.find(Pais.class, input.paisId());

        final Function<Compra, Pedido> funcaoCriacaoPedido = this.funcaoCriacaoPedido(input);

        final Compra novaCompra = Compra.criar(
                input.nome(), input.sobrenome(), input.email(), input.documento(), input.endereco(), input.complemento(),
                input.cidade(), input.cep(), input.telefone(), pais, funcaoCriacaoPedido
        );

        if (input.estadoId() != null) {
            final Estado estado = this.entityManager.find(Estado.class, input.estadoId());

            novaCompra.adicionarEstado(estado);
        }

        if (StringUtils.hasText(input.codigoCupom())) {
            final Cupom cupom = this.cupomRepository.findByCodigo(input.codigoCupom()).orElseThrow();

            novaCompra.aplicarCupom(cupom);
        }

        this.entityManager.persist(novaCompra);
    }

    private Function<Compra, Pedido> funcaoCriacaoPedido(final NovaCompraInput input) {
        final List<Item> itensSelecionados = input.pedido().itens().stream().map(item -> {
            final Livro livro = entityManager.find(Livro.class, item.livroId());

            return Item.criar(item.quantidade(), livro);
        }).toList();

        return (compra) -> {
            final Pedido pedidoCriado = Pedido.criar(compra, itensSelecionados);

            if (!pedidoCriado.totalIgual(input.pedido().total())) throw new IllegalArgumentException("o total enviado não corresponse ao total calculado.");

            return pedidoCriado;
        };
    }

}
