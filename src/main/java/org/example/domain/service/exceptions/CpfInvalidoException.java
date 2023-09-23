package org.example.domain.service.exceptions;

public class CpfInvalidoException extends Exception{
    public CpfInvalidoException() {
        super("Cpf não contém número de caracteres necessários");
    }
}
