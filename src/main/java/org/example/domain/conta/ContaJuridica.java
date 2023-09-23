package org.example.domain.conta;

import org.example.domain.personas.Empresa;
import org.example.domain.analiseCredito.AnaliseCreditoJuridico;
import org.example.domain.analiseCredito.exceptions.CreditoInsuficienteException;
import org.example.domain.financiamento.exceptions.NumeroDeParcelasException;
import org.example.domain.financiamento.Financiamento;
import org.example.domain.financiamento.FinanciamentoPessoaJuridica;
import org.example.domain.financiamento.TipoFinanciamento;
import org.example.domain.investimento.Investimento;
import org.example.domain.investimento.InvestimentoPessoaJuridica;
import org.example.domain.investimento.TipoInvestimento;
import org.example.domain.investimento.exceptions.InvestimentoMaiorQueSaldoException;
import org.example.domain.investimento.exceptions.InvestimentoMininoException;

import java.util.ArrayList;
import java.util.List;

public class ContaJuridica extends Conta {
    private Empresa empresa;
    List<FinanciamentoPessoaJuridica> financiamentosList = new ArrayList<>();
    List<InvestimentoPessoaJuridica> investimentoList = new ArrayList<>();

    public ContaJuridica(String numberAccount, String agency, Double saldo, Empresa empresa) {
        super(numberAccount, agency, saldo);
        this.empresa = empresa;
    }

    @Override
    public Double getAnaliseCredito() {
        return new AnaliseCreditoJuridico(this).creditoDisponivel();
    }

    @Override
    public void setFinanciamento(TipoFinanciamento tipo, Double valor, Integer parcelas) throws NumeroDeParcelasException, CreditoInsuficienteException {
        financiamentosList.add(new FinanciamentoPessoaJuridica(tipo, valor, parcelas, this));
    }

    public List<FinanciamentoPessoaJuridica> imprimirListaFinanciamento() {
        return financiamentosList;
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
        investimentoList.add(new InvestimentoPessoaJuridica(tipo, valor, this));

    }

    public List<InvestimentoPessoaJuridica> imprimirListaInvestimento() {
        return investimentoList;
    }

    @Override
    public void getInvestimento() {
        if (investimentoList.isEmpty()) {
            throw new RuntimeException("Não há investimentos para essa conta");
        }
        for (Investimento i : investimentoList) {
            System.out.println("Investimento de: " + i.getTipo() +
                    " Valor investido: " + i.getValorInvestido() +
                    " Rendimento Ano:  " + i.previsaoDeRendimento(12));
        }
    }
}
