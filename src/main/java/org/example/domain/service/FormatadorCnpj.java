package org.example.domain.service;

import org.example.domain.service.exceptions.CnpjInvalidoException;
import org.example.domain.service.exceptions.CnpjNullException;

public class FormatadorCnpj {
    public String formatarCnpj(String cnpj) throws CnpjNullException, CnpjInvalidoException {
        if (cnpj == null) {
            throw new CnpjNullException();
        }

        String cnpjFormatado = cnpj.replaceAll("\\.|\\/|\\-", "");

        if (cnpjFormatado.length() != 14) {
            throw new CnpjInvalidoException();
        }
        return cnpjFormatado;
    }
}
