package com.gustavo.mag_bank.domain.enums;

public enum TransacaoTipo {

    TED(0, "TED"), PIX(1, "PIX"), CARTAO(2, "CARTAO");

    private Integer codigo;

    private String descricao;

    TransacaoTipo(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TransacaoTipo toEnum(Integer cod){
        if(cod == null) {
            return null;
        }

        for(TransacaoTipo x : TransacaoTipo.values()){
            if(cod.equals((x.getCodigo()))){
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de transação inválida.");
    }
}
