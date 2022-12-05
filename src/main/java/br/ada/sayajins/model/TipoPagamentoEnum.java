package br.ada.sayajins.model;

public enum TipoPagamentoEnum {
    FIDELIDADE,CREDITO,DEBITO,BOLETO,PIX, DESCONHECIDO;

    public static TipoPagamentoEnum tipoEnum(String tipo){
        switch(tipo){
            case "FIDELIDADE":
                return TipoPagamentoEnum.FIDELIDADE;
            case "CREDITO":
                return TipoPagamentoEnum.CREDITO;
            case "DEBITO":
                return TipoPagamentoEnum.DEBITO;
            case "BOLETO":
                return TipoPagamentoEnum.BOLETO;
            case "PIX":
                return TipoPagamentoEnum.PIX;
        }
        return TipoPagamentoEnum.DESCONHECIDO;
    }

}
