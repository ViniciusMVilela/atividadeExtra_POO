package org.example.domain.service;

import org.example.domain.service.exceptions.CnpjInvalidoException;
import org.example.domain.service.exceptions.CnpjNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FormatadorCnpjTest {
    @Test
    public void deveFormatarCnpjValido() throws CnpjInvalidoException, CnpjNullException {
        FormatadorCnpj service = new FormatadorCnpj();
        String resultado = service.formatarCnpj("74.237.124/2126-04");
        Assertions.assertEquals("74237124212604", resultado);
    }

    @Test
    public void deveFormatarCnpjInvalido() throws CnpjInvalidoException, CnpjNullException {
        FormatadorCnpj service = new FormatadorCnpj();

        CnpjInvalidoException resultado = Assertions.assertThrows(CnpjInvalidoException.class,
                () -> service.formatarCnpj("74.237.124/212-04"));
        Assertions.assertEquals("Cnpj Inválido! Não possui a quantidade de caracteres necessários",
                resultado.getMessage());
    }
}
