package org.example.domain;

import org.example.domain.conta.ContaCorrente;
import org.example.domain.conta.exceptions.SaldoInsuficienteException;
import org.example.domain.personas.Pessoa;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ContaSalario {
    @Test
    public void deveRetornarNumeroConta() throws CpfNuloException, CpfInvalidoException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.526.859.-74");
        ContaCorrente conta = new ContaCorrente("1234", "014", 4000.0, vinicius);

       String numeroConta = conta.getNumberAccount();

        Assertions.assertEquals("1234", numeroConta);
    }

    @Test
    public void deveRealizarPagamento() throws CpfNuloException, CpfInvalidoException, SaldoInsuficienteException, ParseException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.526.859.-74");
        ContaCorrente conta = new ContaCorrente("1234", "014", 4000.0, vinicius);

        conta.pagamento(500.0);

        Assertions.assertEquals(3500.0, conta.getSaldo());
    }
}
