package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @NotBlank(message = "Tipo de pagamento é obrigatório")
    @Pattern(regexp = "^(Credito|Debito|Pix|Boleto)$", message = "Tipo deve ser: Credito, Debito, Pix ou Boleto")
    @Column(name = "FPG_TIPO", nullable = false, length = 10)
    private String fpgTipo;

    @NotBlank(message = "Descrição é obrigatório")
    @Size(min = 2, max = 100, message = "Descrição inválida")
    @Column(name = "FPG_DESCRICAO", nullable = false)
    private String fpgDescricao;

    @NotNull(message = "Campo 'Permite Parcelamento' é obrigatório")
    @Column(name = "FPG_PERMITE_PARCELAMENTO", nullable = false)
    private Boolean fpgPermiteParcelamento;

    @NotNull(message = "Número de parcelas deve ser no mínimo 1")
    @Min(value = 1, message = "Número de parcelas deve ser no mínimo 1")
    @Max(value = 12, message = "Número de parcelas não pode exceder 12")
    @Column(name = "FPG_NUM_MAX_PARCELAS", nullable = false)
    private Integer fpgNumMaxParcelas;

    @NotNull(message = "Taxa adicional é obrigatória")
    @DecimalMin(value = "0.00", message = "Taxa adicional não pode ser negativa")
    @Column(name = "FPG_TAXA_ADICIONAL", precision = 5, scale = 2, nullable = false)
    private BigDecimal fpgTaxaAdicional;

    public FormaPagamento() {
    }

    public FormaPagamento(Long fpgId, String fpgTipo, String fpgDescricao, Boolean fpgPermiteParcelamento, Integer fpgNumMaxParcelas, BigDecimal fpgTaxaAdicional) {
        this.fpgId = fpgId;
        this.fpgTipo = fpgTipo;
        this.fpgDescricao = fpgDescricao;
        this.fpgPermiteParcelamento = fpgPermiteParcelamento;
        this.fpgNumMaxParcelas = fpgNumMaxParcelas;
        this.fpgTaxaAdicional = fpgTaxaAdicional;
    }

    // Getters e Setters (mantidos como no original)

    public Long getFpgId() {
        return fpgId;
    }

    public void setFpgId(Long fpgId) {
        this.fpgId = fpgId;
    }

    public String getFpgTipo() {
        return fpgTipo;
    }

    public void setFpgTipo(String fpgTipo) {
        this.fpgTipo = fpgTipo;
    }

    public String getFpgDescricao() {
        return fpgDescricao;
    }

    public void setFpgDescricao(String fpgDescricao) {
        this.fpgDescricao = fpgDescricao;
    }

    public Boolean getFpgPermiteParcelamento() {
        return fpgPermiteParcelamento;
    }

    public void setFpgPermiteParcelamento(Boolean fpgPermiteParcelamento) {
        this.fpgPermiteParcelamento = fpgPermiteParcelamento;
    }

    public Integer getFpgNumMaxParcelas() {
        return fpgNumMaxParcelas;
    }

    public void setFpgNumMaxParcelas(Integer fpgNumMaxParcelas) {
        this.fpgNumMaxParcelas = fpgNumMaxParcelas;
    }

    public BigDecimal getFpgTaxaAdicional() {
        return fpgTaxaAdicional;
    }

    public void setFpgTaxaAdicional(BigDecimal fpgTaxaAdicional) {
        this.fpgTaxaAdicional = fpgTaxaAdicional;
    }
}