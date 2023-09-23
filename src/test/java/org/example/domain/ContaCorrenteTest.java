package org.example.domain;

import org.example.domain.conta.ContaCorrente;
import org.example.domain.conta.exceptions.SaldoInsuficienteException;
import org.example.domain.personas.Pessoa;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ContaCorrenteTest {
    @Test
    public void deveCriarConta() throws CpfNuloException, CpfInvalidoException, ParseException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.526.859.-74");
        ContaCorrente conta = new ContaCorrente("1234", "014", 4000.0, vinicius);

        conta.deposito(100.0);

        Assertions.assertEquals(4100.0, conta.getSaldo());
    }
    @Test
    public void deveNaoPermitirSaque() throws CpfNuloException, CpfInvalidoException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.526.859.-74");
        ContaCorrente conta = new ContaCorrente("1234", "014", 4000.0, vinicius);

        try{
            conta.saque(5000.0);
        } catch (SaldoInsuficienteException e) {
            Assertions.assertEquals("Saldo insuficiente para realizar operação!", e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
