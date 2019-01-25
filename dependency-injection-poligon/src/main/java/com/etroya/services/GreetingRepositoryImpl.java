package com.etroya.services;

/**
 * Created by mada on 25.01.2019.
 */
public class GreetingRepositoryImpl implements GreetingRepository {
    @Override
    public String getEnglishGreeting() {
        return "Hello -Primary Greeting service";
    }

    @Override
    public String getSpanishGreeting() {
        return "Servicio de Saludo Primario";
    }

    @Override
    public String getGermanGreeting() {
        return "Primärer Grußdienst";
    }

    @Override
    public String getDutchGreeting() {
        return "Hej -Primary Hilsen service";
    }
}
