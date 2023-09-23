package org.example.domain.service;

import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;

public class FormatadorCpf {
    public String formatarCpf(String cpf) throws CpfInvalidoException, CpfNuloException {
        String cpfFormatado = cpf.replaceAll("\\.|\\-", "");

        if (cpfFormatado == null) {
            throw new CpfNuloException();
        }
        if (cpfFormatado.length() != 11) {
            throw new CpfInvalidoException();
        }
        return cpfFormatado;
    }
}
