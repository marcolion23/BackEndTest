package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_ID")
    private Long conId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_CLI_ID")
    private Cliente conCliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_FOR_ID")
    private Fornecedor conFornecedor;

    @NotBlank(message = "Celular é obrigatório")
    @Size(max = 20, message = "Celular inválido")
    @Column(name = "CON_CELULAR", nullable = false, length = 20)
    private String conCelular;

    @NotBlank(message = "E-mail é obrigatório")
    @Size(max = 100, message = "E-mail deve ter no máximo 100 caracteres")
    @Column(length = 100, name = "CON_EMAIL", nullable = false)
    private String conEmail;

    public Contato() {
    }

    public Contato(Long conId, Cliente conCliente, String conCelular, String conEmail) {
        this.conId = conId;
        this.conCliente = conCliente;
        this.conCelular = conCelular;
        this.conEmail = conEmail;
    }

    public Contato(Long conId, String conCelular, Fornecedor conFornecedor, String conEmail) {
        this.conId = conId;
        this.conFornecedor = conFornecedor;
        this.conCelular = conCelular;
        this.conEmail = conEmail;
    }

    //getters e setters

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public Cliente getConCliente() {
        return conCliente;
    }

    public void setConCliente(Cliente conCliente) {
        this.conCliente = conCliente;
    }

    public Fornecedor getConFornecedor() {
        return conFornecedor;
    }

    public void setConFornecedor(Fornecedor conFornecedor) {
        this.conFornecedor = conFornecedor;
    }

    public String getConCelular() {
        return conCelular;
    }

    public void setConCelular(String conCelular) {
        this.conCelular = conCelular;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }
}