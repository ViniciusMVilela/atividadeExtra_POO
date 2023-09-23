package org.example.domain.analiseCredito;

import org.example.domain.conta.Conta;

public class AnaliseCreditoJuridico extends AnaliseCredito {


    public AnaliseCreditoJuridico(Conta account) {
        super(account);
    }

    @Override
    public Double creditoDisponivel() {
        return account.getSaldo() * 0.8;
    }
}
