package org.example.domain.analiseCredito;

import org.example.domain.conta.Conta;

public abstract class AnaliseCredito {
    protected Conta account;

    public  AnaliseCredito(Conta account) {
        this.account = account;
    }

    abstract Double creditoDisponivel();
}
