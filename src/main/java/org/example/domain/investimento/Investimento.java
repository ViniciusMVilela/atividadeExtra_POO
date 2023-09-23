package org.example.domain.investimento;

import org.example.domain.conta.Conta;
import org.example.domain.investimento.exceptions.InvestimentoMininoException;

public abstract class Investimento implements ReajusteRendimentoInvestimento {
    private TipoInvestimento tipo;
    private Double valorInvestido;
    protected Double rendimento;
    private Conta account;

    public Investimento(TipoInvestimento tipo, Double valorInvestido, Conta account) throws InvestimentoMininoException {
        if (account.getSaldo() < valorInvestido) {
            throw new RuntimeException("Saldo inferior ao valor de investimento desejado");
        }
        if (valorInvestido < tipo.getValorMinino()) {
            throw new InvestimentoMininoException();
        }
        this.tipo = tipo;
        this.valorInvestido = valorInvestido;
        this.account = account;
        this.rendimento = reajuste();
    }

    public TipoInvestimento getTipo() {
        return tipo;
    }

    public Double getValorInvestido() {
        return valorInvestido;
    }



    public void aporte(Double valor) {
        valorInvestido += valor;
    }

    public Double previsaoDeRendimento(Integer quantidadeMeses) {
        Double valorInvestido = getValorInvestido();
        Double rendimento = getRendimento();

        for(int i = 0; i < quantidadeMeses; i++) {
            double valorRendido = valorInvestido * rendimento;
            valorInvestido += valorRendido;
        }


        return valorInvestido;
    }

    @Override
    public Double reajuste() {
        return tipo.getRendimentoMensal();
    }
    public Double getRendimento() {
        return rendimento;
    }
}
