package pl.etroya.design.adapter;

import java.util.StringTokenizer;

public class EmployeeCsv {

    private int id;
    private String firstname;
    private String lastname;
    private String emailAdress;

    public EmployeeCsv(String values){
        StringTokenizer tokenizer = new StringTokenizer(values, ",");
        if(tokenizer.hasMoreElements()){
            id = Integer.parseInt(tokenizer.nextToken());
        }
        if(tokenizer.hasMoreElements()){
            firstname = tokenizer.nextToken();
        }
        if(tokenizer.hasMoreElements()){
            lastname = tokenizer.nextToken();
        }
        if(tokenizer.hasMoreElements()){
            emailAdress = tokenizer.nextToken();
        }
    }

}
