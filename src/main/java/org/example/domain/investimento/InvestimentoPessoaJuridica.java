package org.example.domain.investimento;

import org.example.domain.conta.Conta;
import org.example.domain.investimento.exceptions.InvestimentoMininoException;

public class InvestimentoPessoaJuridica extends Investimento implements ReajusteRendimentoInvestimento{
    public InvestimentoPessoaJuridica(TipoInvestimento tipo, Double valorInvestido, Conta account) throws InvestimentoMininoException {
        super(tipo, valorInvestido, account);
        super.rendimento = reajuste();
    }

    public Double reajuste() {
        return super.reajuste() + 0.02;
    }
}
