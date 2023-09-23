package org.example.domain.investimento;

public enum TipoInvestimento {
    TESOURO_DIRETO("Tesouro Direto" ,0.08, 1500.0),
    CDB("CDB",0.025, 1000.0),
    RENDA_FIXA("Renda Fixa",0.03, 2000.0);

    private String descricao;
    private Double rendimentoMensal;
    private Double valorMinino;

    TipoInvestimento(String descricao, Double rendimentoMensal, Double valorMinino) {
        this.descricao = descricao;
        this.rendimentoMensal = rendimentoMensal;
        this.valorMinino = valorMinino;
    }

    public Double getRendimentoMensal() {
        return rendimentoMensal;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorMinino() {
        return valorMinino;
    }
}
