package org.example.domain.service.exceptions;

public class CnpjNullException extends Exception{
    // mensagem tem de ter mais que 14 caracteres
    public CnpjNullException() {
        super("Cnpj não pode ser nullo");
    }
}
