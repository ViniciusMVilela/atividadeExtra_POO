package org.example.domain.service.exceptions;

public class CpfNuloException extends Exception{
    public CpfNuloException() {
        super("Cpf informado não pode ser nulo!");
    }
}
