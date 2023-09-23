package org.example.domain.investimento.exceptions;

public class InvestimentoMininoException extends Exception{
    public InvestimentoMininoException() {
        super("Valor mínino de investimento não atingido");
    }
}
