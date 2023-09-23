package org.example.domain.analiseCredito;

import org.example.domain.conta.Conta;

public class AnaliseCreditoPessoa extends AnaliseCredito {


    public AnaliseCreditoPessoa(Conta account) {
        super(account);
    }

    @Override
    public Double creditoDisponivel() {
        return account.getSaldo() * 0.65;
    }
}
