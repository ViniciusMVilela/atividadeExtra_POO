package org.example.domain.conta.exceptions;

public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String msg){
        super(msg);
    }
}
