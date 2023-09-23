package org.example.domain;

import org.example.domain.conta.ContaConjunta;
import org.example.domain.conta.TipoVinculo;
import org.example.domain.personas.Pessoa;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class ContaConjuntaTest {
    @Test
    public void deveCriarContaJunta() throws CpfNuloException, CpfInvalidoException, ParseException {
        Pessoa marcos = new Pessoa("Marcos", "123.123.569-89");
        Pessoa maria = new Pessoa("Maria", "853.193.569-89");

        ContaConjunta contaConjunta = new ContaConjunta("2563", "031", 5000.0, marcos, maria,
                TipoVinculo.CASAMENTO);

        contaConjunta.deposito(100.0);

        Assertions.assertEquals(5100.0, contaConjunta.getSaldo());
    }
}
