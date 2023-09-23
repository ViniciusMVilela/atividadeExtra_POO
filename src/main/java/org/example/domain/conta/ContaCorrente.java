package org.example.domain.conta;

import org.example.domain.personas.Pessoa;
import org.example.domain.analiseCredito.AnaliseCreditoPessoa;
import org.example.domain.analiseCredito.exceptions.CreditoInsuficienteException;
import org.example.domain.financiamento.Financiamento;
import org.example.domain.financiamento.FinanciamentoContaPessoa;
import org.example.domain.financiamento.TipoFinanciamento;
import org.example.domain.financiamento.exceptions.NumeroDeParcelasException;
import org.example.domain.investimento.Investimento;
import org.example.domain.investimento.InvestimentoContaPessoa;
import org.example.domain.investimento.TipoInvestimento;
import org.example.domain.investimento.exceptions.InvestimentoMaiorQueSaldoException;
import org.example.domain.investimento.exceptions.InvestimentoMininoException;

import java.util.List;

public class ContaCorrente extends Conta{
    private Pessoa pessoa;

    List<FinanciamentoContaPessoa> financiamento;
    List<InvestimentoContaPessoa> investimento;

    public ContaCorrente(String numberAccount, String agency, Double saldo, Pessoa pessoa) {
        super(numberAccount, agency, saldo);
        this.pessoa = pessoa;
    }

    @Override
    public Double getAnaliseCredito() {
        return (new AnaliseCreditoPessoa(this).creditoDisponivel());
    }

    @Override
    public void setFinanciamento(TipoFinanciamento tipo, Double valor, Integer parcelas) throws NumeroDeParcelasException, CreditoInsuficienteException {
        financiamento.add(new FinanciamentoContaPessoa(tipo, valor, parcelas, this));
    }

    public List<FinanciamentoContaPessoa> imprimirListaFinanciamento() {
        return financiamento;
    }

    @Override
    public void imprimirFinanciamento() {
        if (financiamentosList.isEmpty()) {
            throw new RuntimeException("financiamento vazio");
        }
        for (Financiamento f : financiamentosList) {
            System.out.println("Financiamento de: " + f.getFinanciamento() +
                    " Valor: " + f.getValorTotal() +
                    " Número de parcelas: " + f.getNumeroParcelas() +
                    " Valor das parcelas: " + String.format("%.2f", f.getValorParcelas()));
        }
    }

    @Override
    public void setInvestimento(TipoInvestimento tipo, Double valor) throws InvestimentoMaiorQueSaldoException, InvestimentoMininoException {
        if (valor > getSaldo()) {
            throw new InvestimentoMaiorQueSaldoException();
        }
        investimento.add(new InvestimentoContaPessoa(tipo, valor, this));
    }

    public List<InvestimentoContaPessoa> imprimirListaInvestimento() {
        return investimento;
    }

    @Override
    public void getInvestimento() {
        if (investimento.isEmpty()) {
            throw new RuntimeException("Não há investimentos para essa conta");
        }
        for (Investimento i : investimento) {
            System.out.println("Investimento de: " + i.getTipo() +
                    " Valor investido: " + i.getValorInvestido() +
                    " Rendimento Ano:  " + i.previsaoDeRendimento(12));
        }
    }
}
