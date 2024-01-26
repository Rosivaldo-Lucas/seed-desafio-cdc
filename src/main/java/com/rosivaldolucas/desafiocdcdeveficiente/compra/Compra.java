package com.rosivaldolucas.desafiocdcdeveficiente.compra;

import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import jakarta.persistence.*;

import java.util.function.Function;

@Entity
@Table(name = "COMPRA")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String email;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private String cep;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS")
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO")
    private Estado estado;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    protected Compra() { }

    private Compra(final Long id, final String nome, final String sobrenome, final String email, final String documento, final String endereco, final String complemento, final String cidade, final String cep, final String telefone, final Pais pais, final Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.cep = cep;
        this.telefone = telefone;
        this.pais = pais;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    public static Compra criar(final String nome, final String sobrenome, final String email, final String documento, final String endereco, final String complemento, final String cidade, final String cep, final String telefone, final Pais pais, final Function<Compra, Pedido> funcaoCriacaoPedido) {
        return new Compra(null, nome, sobrenome, email, documento, endereco, complemento, cidade, cep, telefone, pais, funcaoCriacaoPedido);
    }

    public void adicionarEstado(final Estado estado) {
        this.estado = estado;
    }

}
