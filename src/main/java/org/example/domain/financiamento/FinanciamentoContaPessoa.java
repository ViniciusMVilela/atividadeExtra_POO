package org.example.domain.financiamento;

import org.example.domain.analiseCredito.exceptions.CreditoInsuficienteException;
import org.example.domain.conta.Conta;
import org.example.domain.financiamento.exceptions.NumeroDeParcelasException;

public class FinanciamentoContaPessoa extends Financiamento implements ReajusteJurosFinanciamento {
    Double jurosPessoa = 1.7;

    public FinanciamentoContaPessoa(TipoFinanciamento financiamento, Double valorFinanciamento, Integer numeroParcelas, Conta account) throws CreditoInsuficienteException, NumeroDeParcelasException {
        super(financiamento, valorFinanciamento, numeroParcelas, account);
        super.juros = jurosPessoa;
    }

    @Override
    public void setJuros(Double juros) {
        super.setJuros(1.5);
    }

    @Override
    public Double reajuste() {
        return 0.75;
    }

}
