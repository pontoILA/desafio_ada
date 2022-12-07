package br.ada.sayajins.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

public class Pagamentos {
    private String nome;

    private LocalDate dtVencto;

    public TipoPagamentoEnum getTipoPagamentoEnum() {
        return tipoPagamentoEnum;
    }

    public void setTipoPagamentoEnum(TipoPagamentoEnum tipoPagamentoEnum) {
        this.tipoPagamentoEnum = tipoPagamentoEnum;
    }

    private BigDecimal valor;

    private TipoPagamentoEnum tipoPagamentoEnum;

    public LocalDate getDtVencto() {
        return dtVencto;
    }

    public void setDtVencto(LocalDate dtVencto) {
        this.dtVencto = dtVencto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String v) {
        this.nome = v;
    }


    public Pagamentos(String s){
        setNome(s);
    }

    public Pagamentos(String s, LocalDate dateVencto, double valor, TipoPagamentoEnum tipoPagamentoEnum){
        this.valor=BigDecimal.valueOf(valor);
        this.nome=s;
        this.dtVencto=dateVencto;
        this.tipoPagamentoEnum=tipoPagamentoEnum;
    }

    private static String verificaAtraso(LocalDate data){
        return data.isBefore(LocalDate.now())?""+Math.abs(Period.between(data, LocalDate.now()).getMonths()):"0";
    }

    private static String verificarAdianto(LocalDate data){
        return data.isAfter(LocalDate.now())? ""+Math.abs(Period.between(data, LocalDate.now()).getDays()) :"0";
    }

    public void gerarAcrescimo(){
        switch(this.tipoPagamentoEnum){
            case CREDITO:
                this.valor = this.valor.add(this.valor.multiply(new BigDecimal("0.03")).multiply(new BigDecimal(verificaAtraso(dtVencto))));
                break;
            case DEBITO:
                this.valor = this.valor.add(this.valor.multiply(new BigDecimal("0.01")).multiply(new BigDecimal(verificaAtraso(dtVencto))));
                break;
            case BOLETO:
                this.valor = this.valor.add(this.valor.multiply(new BigDecimal("0.05")).multiply(new BigDecimal(verificaAtraso(dtVencto))));
                break;
            default:
                break;
        }
    }

    public void gerarDecrescimo(){
        switch(this.tipoPagamentoEnum){
            case FIDELIDADE:
                this.valor = this.valor.subtract(this.valor.multiply(new BigDecimal("0.005")).multiply(new BigDecimal(verificarAdianto(dtVencto))));
                break;
            case PIX:
                this.valor = this.valor.subtract(this.valor.multiply(new BigDecimal("0.005")).multiply(new BigDecimal(verificarAdianto(dtVencto))));
                break;
            default:
                break;
        }
    }


    public String getValorFormatado(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
        return  formatter.format(this.valor.doubleValue());
    }

    // Mateus esteve aqui
    @Override

    public String toString(){

        return String.format("%30s | ", this.nome)

                   .concat(String.format("%10s | ", this.dtVencto.toString()))

                   .concat(String.format("%12s | ", this.getValorFormatado()))

                   .concat(String.format("%10s", this.getTipoPagamentoEnum().name()));

    }
}
