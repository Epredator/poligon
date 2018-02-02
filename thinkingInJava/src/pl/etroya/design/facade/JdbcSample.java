package pl.etroya.design.facade;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSample {
    public static void main(String args[]){
        DbSingleton instance = DbSingleton.getInstance();

        try{
            Connection conn = instance.getConnection();
            Statement sta = conn.createStatement();
            int count = sta.executeUpdate(("CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(20), City VARCHAR(20)"));
            System.out.println("Table crated");
            sta.close();

            sta = conn.createStatement();
            count = sta.executeUpdate("INSERT INTO Address(ID, StreetName, City) values (1, '12 Street Example', 'London')");
            System.out.println(count + " records created");
            sta.close();

            sta = conn.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM Address");

            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
