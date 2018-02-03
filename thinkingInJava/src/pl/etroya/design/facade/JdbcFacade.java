package pl.etroya.design.facade;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcFacade {
    DbSingleton instance = null;

    public JdbcFacade(){
        instance = DbSingleton.getInstance();

    }


    public int createTable(){
        int count = 0;
        try {
            Connection c = instance.getConnection();
            Statement sta =  c.createStatement();
            count = sta.executeUpdate(("CREATE TABLE Address (ID INTEGER, StreetName VARCHAR(20), City VARCHAR(20)"));
            sta.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    public int insertIntoTable(){
        int count = 0;
        try {
            Connection c = instance.getConnection();
            Statement sta =  c.createStatement();
            count = sta.executeUpdate("INSERT INTO Address(ID, StreetName, City) values (1, '12 Street Example', 'London')");
            sta.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }

    public List<Address> getAddresses() throws SQLException {
        Connection c = instance.getConnection();
        Statement sta =  c.createStatement();
        sta = c.createStatement();
        ResultSet rs = null;
        List<Address> addresses = new ArrayList<Address>();
        try {
            rs = sta.executeQuery("SELECT * FROM Address");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while(rs.next()){
            Address address = new Address();
            address.setId(rs.getString(1));
            address.setStreetName(rs.getString(2));
            address.setCity(rs.getString(3));
            addresses.add(address);

            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }

        return addresses;
    }
}

class Address{
    private String id;
    private String streetName;
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
