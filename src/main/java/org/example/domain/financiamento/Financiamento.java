package org.example.domain.financiamento;

import org.example.domain.conta.Conta;
import org.example.domain.analiseCredito.exceptions.CreditoInsuficienteException;
import org.example.domain.financiamento.exceptions.NumeroDeParcelasException;

public class Financiamento implements ReajusteJurosFinanciamento {
    private TipoFinanciamento financiamento;
    private Double valorFinanciamento;
    private Integer numeroParcelas;
    private Double valorParcelas;
    protected Double juros;
    private Double valorTotal;
    private Conta account;


    public Financiamento(TipoFinanciamento financiamento, Double valorFinanciamento, Integer numeroParcelas, Conta account) throws CreditoInsuficienteException, NumeroDeParcelasException {
        if (valorFinanciamento > account.getAnaliseCredito()) {
            throw new CreditoInsuficienteException("Crédito insuficiente, consultar análise de crédito!");
        }
        if (numeroParcelas < 5 || numeroParcelas > 40) {
            throw new NumeroDeParcelasException();
        }
        this.financiamento = financiamento;
        this.valorFinanciamento = valorFinanciamento;
        this.numeroParcelas = numeroParcelas;
        this.juros = reajuste();
        this.valorTotal = valorFinanciamento * (1 + juros);
        this.valorParcelas = valorTotal / numeroParcelas;
        this.account = account;

    }

    public TipoFinanciamento getFinanciamento() {
        return financiamento;
    }

    public Double getValorFinanciamento() {
        return valorFinanciamento;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public Double getJuros() {
        return juros;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Double getValorParcelas() {
        return valorParcelas;
    }

    @Override
    public Double reajuste() {
        return 0.5;
    }


}
