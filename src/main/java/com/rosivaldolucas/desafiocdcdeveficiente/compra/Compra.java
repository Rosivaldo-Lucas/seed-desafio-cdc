package com.rosivaldolucas.desafiocdcdeveficiente.compra;

import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Estado;
import com.rosivaldolucas.desafiocdcdeveficiente.paisestado.Pais;
import jakarta.persistence.*;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "PAGAMENTO")
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

    protected Compra() { }

    private Compra(final Long id, final String nome, final String sobrenome, final String email, final String documento, final String endereco, final String complemento, final String cidade, final String cep, final String telefone, final Pais pais, final Estado estado) {
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
        this.estado = estado;
    }

    public static Compra criar(final String nome, final String sobrenome, final String email, final String documento, final String endereco, final String complemento, final String cidade, final String cep, final String telefone, final Pais pais, final Estado estado) {
        return new Compra(null, nome, sobrenome, email, documento, endereco, complemento, cidade, cep, telefone, pais, estado);
    }

}
