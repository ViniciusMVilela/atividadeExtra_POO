package org.example.domain.financiamento;

public enum TipoFinanciamento {
    VEICULO("Veíuclo", 0.25),
    IMOVEL("Imóvel", 0.32);

    private String descricao;
    private Double percentual;

    TipoFinanciamento(String descricao, Double percentual) {
        this.descricao = descricao;
        this.percentual = percentual;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPercentual() {
        return percentual;
    }
}
