package org.example.domain.personas;

import org.example.domain.service.exceptions.CnpjInvalidoException;
import org.example.domain.service.exceptions.CnpjNullException;
import org.example.domain.service.FormatadorCnpj;

public class Empresa {
    private String nome;
    private String cnpj;

    public Empresa(String nome, String cnpj) throws CnpjInvalidoException, CnpjNullException {
        if (cnpjValido(cnpj)) {
            this.nome = nome;
            this.cnpj = cnpj;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public boolean cnpjValido(String cnpj) throws CnpjInvalidoException, CnpjNullException {
        FormatadorCnpj formatadorCnpj = new FormatadorCnpj();
        formatadorCnpj.formatarCnpj(cnpj);
        if (formatadorCnpj.formatarCnpj(cnpj).length() != 14) {
            return false;
        }
        return true;
    }
}
