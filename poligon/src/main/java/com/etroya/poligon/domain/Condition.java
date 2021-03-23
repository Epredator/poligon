package com.etroya.poligon.domain;

public enum Condition {
    HOT("Warning hot!"),
    WARM("Warning warm!"),
    COLD("Warning cold!");
    private String caution;

    private Condition(String caution){
        this.caution = caution;
    }

    public String getCaution(){
        return caution;
    }
}
