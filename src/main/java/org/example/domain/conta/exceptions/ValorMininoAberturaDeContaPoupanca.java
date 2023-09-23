package org.example.domain.conta.exceptions;

public class ValorMininoAberturaDeContaPoupanca extends Exception{
    public ValorMininoAberturaDeContaPoupanca() {
        super("Valor mínimo de 50.0 para abertura de conta!");
    }
}
