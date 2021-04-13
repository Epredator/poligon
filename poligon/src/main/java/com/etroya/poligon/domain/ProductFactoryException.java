package com.etroya.poligon.domain;

import java.util.Properties;

public class ProductFactoryException extends Exception{

    public ProductFactoryException(){
        super();
    }

    public ProductFactoryException(String message){
        super(message);
    }

    public ProductFactoryException(String message, Throwable cause){
        super(message);
    }

}


