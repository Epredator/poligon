package pl.etroya.design.adapter;

import java.util.StringTokenizer;

public class EmployeeCsv {

    private int id;
    private String firstname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

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
