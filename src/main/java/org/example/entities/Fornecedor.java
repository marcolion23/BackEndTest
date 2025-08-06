package org.example.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOR_ID")
    private Long forId;

    // Relacionamento com Produto
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    @JsonManagedReference  // controla o lado "pai" da relação
    private List<Produto> produtos = new ArrayList<>();

    // Relacionamento com Endereço
    @OneToMany(mappedBy = "endFornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    // Relacionamento com Contato
    @OneToMany(mappedBy = "conFornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

    @NotBlank(message = "Nome Fantasia é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(name = "FOR_NOME_FANTASIA", nullable = false, length = 100)
    private String forNomeFantasia;

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido")
    @Column(name = "FOR_CNPJ", nullable = false, unique = true, length = 20)
    private String forCnpj;

    @NotBlank(message = "Razão social é obrigatório")
    @Size(max = 100, message = "Razão social deve ter no máximo 100 caracteres")
    @Column(name = "FOR_RAZAO_SOCIAL", nullable = false, length = 100)
    private String forRazaoSocial;

    public Fornecedor() {
    }

    public Fornecedor(Long forId, String forNomeFantasia, String forCnpj, String forRazaoSocial) {
        this.forId = forId;
        this.forNomeFantasia = forNomeFantasia;
        this.forCnpj = forCnpj;
        this.forRazaoSocial = forRazaoSocial;
    }

    public Long getForId() {
        return forId;
    }

    public void setForId(Long forId) {
        this.forId = forId;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public String getForNomeFantasia() {
        return forNomeFantasia;
    }

    public void setForNomeFantasia(String forNomeFantasia) {
        this.forNomeFantasia = forNomeFantasia;
    }

    public String getForCnpj() {
        return forCnpj;
    }

    public void setForCnpj(String forCnpj) {
        this.forCnpj = forCnpj;
    }

    public String getForRazaoSocial() {
        return forRazaoSocial;
    }

    public void setForRazaoSocial(String forRazaoSocial) {
        this.forRazaoSocial = forRazaoSocial;
    }
}