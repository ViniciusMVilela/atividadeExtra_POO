package org.example.domain.personas;

import org.example.domain.service.FormatadorCpf;
import org.example.domain.service.exceptions.CpfInvalidoException;
import org.example.domain.service.exceptions.CpfNuloException;

public class Pessoa {
    private String name;
    private String cpf;

    public Pessoa(String name, String cpf) throws CpfNuloException, CpfInvalidoException {
        if (cpfValido(cpf)) {
            this.name = name;
            this.cpf = cpf;
        }
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean cpfValido(String cpf) throws CpfNuloException, CpfInvalidoException {
        FormatadorCpf formatadorCpf = new FormatadorCpf();
        formatadorCpf.formatarCpf(cpf);
        if (formatadorCpf.formatarCpf(cpf).length() != 11) {
            return false;
        }
        return true;
    }
}
