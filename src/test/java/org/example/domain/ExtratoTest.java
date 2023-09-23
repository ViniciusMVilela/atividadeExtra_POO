package org.example.domain;

import org.example.domain.conta.ContaPoupanca;
import org.example.domain.conta.exceptions.ValorMininoAberturaDeContaPoupanca;
import org.example.domain.personas.Pessoa;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

public class ExtratoTest {
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
