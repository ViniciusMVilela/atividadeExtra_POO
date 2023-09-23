package org.example.domain.financiamento.exceptions;

public class NumeroDeParcelasException extends Exception{
    public NumeroDeParcelasException(){
        super("Número de parcelas não autorizado");
    }
}
