package com.etroya.poligon.domain;

public class GenericSample<T>{
    private T value;
    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }
}
