package org.example.domain.investimento.exceptions;

public class InvestimentoMaiorQueSaldoException extends Exception{
    public InvestimentoMaiorQueSaldoException() {
        super("O investimento não pode ser maior que o saldo em conta!");
    }
}
