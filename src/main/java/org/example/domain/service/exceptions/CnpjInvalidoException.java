package org.example.domain.service.exceptions;

public class CnpjInvalidoException extends Exception{
    // mensagem tem de ter mais que 14 caracteres
    public CnpjInvalidoException() {
        super("Cnpj Inválido! Não possui a quantidade de caracteres necessários");
    }
}
