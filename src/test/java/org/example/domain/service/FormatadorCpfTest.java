package org.example.domain.service;

import org.example.domain.personas.Pessoa;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormatadorCpfTest {
    @Test
    public void deveFormatarCpfValido() throws CpfNuloException, CpfInvalidoException {
        Pessoa marcos = new Pessoa("Marcos", "256.325.560-89");
        FormatadorCpf cpf = new FormatadorCpf();

        Assertions.assertEquals("25632556089", cpf.formatarCpf(marcos.getCpf()));
    }
    @Test
    public void deveFormatarCpfInvalido() throws CpfNuloException, CpfInvalidoException {
        try {
            Pessoa felipe = new Pessoa("Felipe", "856.32.560-89");
        } catch (CpfInvalidoException e) {
            Assertions.assertEquals("Cpf não contém número de caracteres necessários", e.getMessage());
        }



    }
}
