package org.example.domain;

import org.example.domain.conta.ContaPoupanca;
import org.example.domain.conta.exceptions.ValorMininoAberturaDeContaPoupanca;
import org.example.domain.personas.Pessoa;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;

import java.text.ParseException;


public class Test {
    public static void main(String[] args) throws CpfNuloException, CpfInvalidoException, ValorMininoAberturaDeContaPoupanca, ParseException {
        Pessoa vinicius = new Pessoa("Vinicius", "123.123.257-77");
        ContaPoupanca conta = new ContaPoupanca("12356", "013", 1000.0, vinicius);

        conta.deposito(200.0);
        conta.deposito(300.0);
        conta.deposito(100.0, "22/02/2023 14:32:23");

        conta.imprimirExtrato();
        conta.imprimirExtratoMes("02");
    }
}
