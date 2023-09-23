package org.example.domain.conta;

import org.example.domain.analiseCredito.exceptions.CreditoInsuficienteException;
import org.example.domain.financiamento.exceptions.NumeroDeParcelasException;
import org.example.domain.conta.exceptions.SaldoInsuficienteException;
import org.example.domain.extrato.Operacao;
import org.example.domain.extrato.TipoOperacao;
import org.example.domain.financiamento.Financiamento;
import org.example.domain.financiamento.TipoFinanciamento;
import org.example.domain.investimento.Investimento;
import org.example.domain.investimento.InvestimentoPessoaJuridica;
import org.example.domain.investimento.TipoInvestimento;
import org.example.domain.investimento.exceptions.InvestimentoMaiorQueSaldoException;
import org.example.domain.investimento.exceptions.InvestimentoMininoException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private String numberAccount;
    private String agency;
    private Double saldo;

    List<Operacao> extratos = new ArrayList<>();
    List<Financiamento> financiamentosList = new ArrayList<>();
    List<Investimento> investimentoList = new ArrayList<>();

    public Conta(String numberAccount, String agency, Double saldo) {
        this.numberAccount = numberAccount;
        this.agency = agency;
        this.saldo = saldo;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public String getAgency() {
        return agency;
    }

    public Double getSaldo() {
        return saldo;

    }

    public void deposito(Double valor) throws ParseException {
        saldo += valor;
        extratos.add(new Operacao(TipoOperacao.DEPOSITO, valor));

    }

    public void deposito(Double valor, String data) throws ParseException {
        saldo += valor;
        extratos.add(new Operacao(TipoOperacao.DEPOSITO, valor, data));

    }

    public void saque(Double valor) throws SaldoInsuficienteException, ParseException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar operação!");
        }
        saldo -= valor;
        extratos.add(new Operacao(TipoOperacao.SAQUE, valor));

    }

    public void saque(Double valor, String data) throws SaldoInsuficienteException, ParseException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar operação!");
        }
        saldo -= valor;
        extratos.add(new Operacao(TipoOperacao.SAQUE, valor, data));

    }

    public void pagamento(Double valor) throws SaldoInsuficienteException, ParseException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar operação!");
        }
        saldo -= valor;
        extratos.add(new Operacao(TipoOperacao.PAGAMENTO, valor));
    }

    public void pagamento(Double valor, String data) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar operação!");
        }
        saldo -= valor;
        extratos.add(new Operacao(TipoOperacao.PAGAMENTO, valor, data));
    }

    public List<Operacao> imprimirListExtrato() {
        return extratos;
    }

    public void imprimirExtrato() {
        for (Operacao p : extratos) {
            System.out.println(p.getOperacao() + " " + p.getValor() + " " + p.getData());
        }
    }

    public List<Operacao> imprimirListaExtratoMes(String mes) {
        List<Operacao> filtro = new ArrayList<>();

        for (Operacao p : extratos) {
            String data = p.getData();
            String resultado = data.substring(3, 5);
            if (resultado.equals(mes)) {
                filtro.add(p);
            }
        }
        return filtro;
    }

    public void imprimirExtratoMes(String mes) {
        List<Operacao> filtro = new ArrayList<>();

        for (Operacao p : extratos) {
            String data = p.getData();
            String resultado = data.substring(3, 5);
            if (resultado.equals(mes)) {
                filtro.add(p);
            }
        }

        for (Operacao f : filtro) {
            System.out.print("Extrato mês: " + mes + "\n" + f.getOperacao() + " " + f.getValor() + " " + f.getData());
        }
    }

    public abstract Double getAnaliseCredito();

    public abstract void setFinanciamento(TipoFinanciamento tipo, Double valor, Integer parcelas) throws NumeroDeParcelasException, CreditoInsuficienteException;

    public abstract void imprimirFinanciamento();

    public abstract void setInvestimento(TipoInvestimento tipo, Double valor) throws InvestimentoMaiorQueSaldoException, InvestimentoMininoException;

    public abstract void getInvestimento();

}
