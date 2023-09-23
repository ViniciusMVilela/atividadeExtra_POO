package org.example.domain.extrato;

import java.util.List;

public class Extrato {
    private List<Operacao> operacaoList;

    public Extrato(List<Operacao> operacaoList) {
        this.operacaoList = operacaoList;
    }
}
