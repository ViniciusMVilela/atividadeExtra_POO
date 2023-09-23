package org.example.domain;

import org.example.domain.personas.Empresa;
import org.example.domain.analiseCredito.exceptions.CreditoInsuficienteException;
import org.example.domain.conta.ContaJuridica;
import org.example.domain.conta.exceptions.SaldoInsuficienteException;
import org.example.domain.service.exceptions.CnpjInvalidoException;
import org.example.domain.service.exceptions.CnpjNullException;
import org.example.domain.extrato.Operacao;
import org.example.domain.financiamento.FinanciamentoPessoaJuridica;
import org.example.domain.financiamento.TipoFinanciamento;
import org.example.domain.financiamento.exceptions.NumeroDeParcelasException;
import org.example.domain.investimento.InvestimentoPessoaJuridica;
import org.example.domain.investimento.TipoInvestimento;
import org.example.domain.investimento.exceptions.InvestimentoMaiorQueSaldoException;
import org.example.domain.investimento.exceptions.InvestimentoMininoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

public class ContaJuridicaTest {

    @Test
    public void deveDepositarValorNaConta() throws ParseException, CnpjInvalidoException, CnpjNullException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        conta.deposito(100.0);

        Assertions.assertEquals(5100.0, conta.getSaldo());
    }

    @Test
    public void deveRealizarSaqueNaConta() throws SaldoInsuficienteException, ParseException, CnpjInvalidoException, CnpjNullException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        conta.saque(100.0);

        Assertions.assertEquals(4900.0, conta.getSaldo());
    }

    @Test
    public void deveFalharSaqueNaContaPorSaldoInsuficiente() throws CnpjInvalidoException, CnpjNullException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        try {
            conta.saque(6000.0);
            Assertions.assertEquals(0d, conta.getSaldo());

        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("Saldo insuficiente para realizar operação!", e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deveRetornarAnaliseCredito() throws CnpjInvalidoException, CnpjNullException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        Double credito = conta.getAnaliseCredito();

        Assertions.assertEquals(4000.0, credito);
    }

    @Test
    public void deveRetornarTamanhoListaDeFinanciamento() throws NumeroDeParcelasException, CreditoInsuficienteException, CnpjInvalidoException, CnpjNullException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);
        conta.setFinanciamento(TipoFinanciamento.IMOVEL, 3000.0, 6);

        List<FinanciamentoPessoaJuridica> finaciamentos = conta.imprimirListaFinanciamento();

        Assertions.assertEquals(1, finaciamentos.size());
    }

    @Test
    public void deveRetornarTamanhoListaDeInvestimento() throws NumeroDeParcelasException, CreditoInsuficienteException, CnpjInvalidoException, CnpjNullException, InvestimentoMaiorQueSaldoException, InvestimentoMininoException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);
        conta.setFinanciamento(TipoFinanciamento.IMOVEL, 3000.0, 6);

        conta.setInvestimento(TipoInvestimento.CDB, 1000.0);

        List<InvestimentoPessoaJuridica> investimentos = conta.imprimirListaInvestimento();

        Assertions.assertEquals(1, investimentos.size());

    }

    @Test
    public void deveNaoPermitirValorMinimoDeInvestimento() throws CnpjInvalidoException, CnpjNullException, InvestimentoMaiorQueSaldoException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        try {
            conta.setInvestimento(TipoInvestimento.CDB, 100.0);

        } catch (InvestimentoMininoException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("Valor mínino de investimento não atingido", e.getMessage());
        }


    }

    @Test
    public void deveRetornarTamanhoExtrato() throws CnpjInvalidoException, CnpjNullException, ParseException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        conta.deposito(100.0);
        conta.deposito(100.0);

        List<Operacao> extrato = conta.imprimirListExtrato();

        Assertions.assertEquals(2, extrato.size());
    }

    @Test
    public void deveRetornarTamanhoExtratoPorMes() throws CnpjInvalidoException, CnpjNullException, ParseException {
        Empresa viniTechLtda = new Empresa("ViniTech LTDA", "74.237.124/2126-04");
        ContaJuridica conta = new ContaJuridica("123", "013A", 5000.0, viniTechLtda);

        conta.deposito(100.0);
        conta.deposito(100.0);
        conta.deposito(100.0, "22/02/2023 14:45:22");

        List<Operacao> extrato = conta.imprimirListExtrato();
        List<Operacao> extratoMesDois = conta.imprimirListaExtratoMes("02");

        Assertions.assertEquals(3, extrato.size());
        Assertions.assertEquals(1, extratoMesDois.size());
    }
}


