package org.example.domain.extrato;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Operacao {
    private TipoOperacao operacao;
    private Double valor;
    private String data;

    public Operacao(TipoOperacao operacao, Double valor) throws ParseException {
        Date dataAtual = new Date();
        String dataFormatada = formato.format(dataAtual);
        this.operacao = operacao;
        this.valor = valor;
        this.data = dataFormatada;
    }

    public Operacao(TipoOperacao operacao, Double valor, String data) {
        this.operacao = operacao;
        this.valor = valor;
        this.data = data;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }

    public Double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    // Define o formato desejado
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

}
