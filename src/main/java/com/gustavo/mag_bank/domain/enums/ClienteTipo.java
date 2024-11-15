package com.gustavo.mag_bank.domain.enums;

public enum ClienteTipo {

    ADMIN(0, "ROLE_ADMIN"), NORMAL(1, "ROLE_NORMAL");

    private Integer codigo;

    private String descricao;

    ClienteTipo(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ClienteTipo toEnum(Integer cod){
        if(cod == null) {
            return null;
        }

        for(ClienteTipo x : ClienteTipo.values()){
            if(cod.equals((x.getCodigo()))){
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de cliente inválido.");
    }

}
