package org.example.domain;

import org.example.domain.conta.exceptions.SaldoInsuficienteException;
import org.example.domain.personas.Pessoa;
import org.example.domain.conta.ContaPoupanca;
import org.example.domain.conta.exceptions.ValorMininoAberturaDeContaPoupanca;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

public class ContaPoupancaTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(stdout);
    }

    @Test
    public void naoDevePermitirCriarConta() throws CpfNuloException, CpfInvalidoException, ValorMininoAberturaDeContaPoupanca {
        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");

        try {
            ContaPoupanca conta = new ContaPoupanca("12356", "013", 10.0, vinicius);
        } catch (ValorMininoAberturaDeContaPoupanca e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("Valor mínimo de 50.0 para abertura de conta!", e.getMessage());
        }
    }

    @Test
    public void deveDepositarValorNaConta() throws ParseException, ValorMininoAberturaDeContaPoupanca, CpfNuloException, CpfInvalidoException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");
        ContaPoupanca conta = new ContaPoupanca("12356", "013", 100.0, vinicius);

        conta.deposito(100.0);

        Assertions.assertEquals(200.0, conta.getSaldo());
    }

    @Test
    public void deveFalharSaqueNaContaSaldoInsuficiente() throws CpfNuloException, CpfInvalidoException, ValorMininoAberturaDeContaPoupanca {
        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");
        ContaPoupanca conta = new ContaPoupanca("12356", "013", 100.0, vinicius);

        try {
            conta.saque(200.0);
        } catch (SaldoInsuficienteException e) {
            Assertions.assertEquals("Saldo insuficiente para realizar operação!", e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deveRetornarAnaliseDeCredito() throws CpfNuloException, CpfInvalidoException, ValorMininoAberturaDeContaPoupanca {
        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");
        ContaPoupanca conta = new ContaPoupanca("12356", "013", 1000.0, vinicius);

        Double credito = conta.getAnaliseCredito();

        Assertions.assertEquals(650.0, credito);
    }
    @Test void deveRetornarExtratoMesDois() throws CpfNuloException, CpfInvalidoException, ValorMininoAberturaDeContaPoupanca, ParseException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");
        ContaPoupanca conta = new ContaPoupanca("12356", "013", 1000.0, vinicius);

        conta.deposito(200.0);
        conta.deposito(300.0);
        conta.deposito(100.0, "22/02/2023 14:32:23");
    }
    @Test
    public void testImprimirMensagem() throws CpfNuloException, CpfInvalidoException, ValorMininoAberturaDeContaPoupanca, ParseException {

        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");
        ContaPoupanca conta = new ContaPoupanca("12356", "013", 1000.0, vinicius);

        conta.deposito(200.0);
        conta.deposito(300.0);
        conta.deposito(100.0, "22/02/2023 14:32:23");

        conta.imprimirExtratoMes("02");
        String mensagemEsperada = "Extrato mês: 02\n" +
                "DEPOSITO 100.0 22/02/2023 14:32:23";
        String mensagemReal = output.toString();
        Assertions.assertEquals(mensagemEsperada, mensagemReal);
    }
}
