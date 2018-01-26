package pl.etroya.design.adapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClient {

    public List<Employee> getEmployeeList() {
        List<Employee> employees= new ArrayList<>();
        Employee employeeFromDB = new EmployeeDB("12345", "Stefan", "Testowy", "stefan@mail.pl");
        employees.add(employeeFromDB);

        EmployeeLdap employeeLdap = new EmployeeLdap("chewie", "Solo", "Han", "han.solo@etroya.pl");
        employees.add(new EmployeeAdapterLdap(employeeLdap));

        EmployeeCsv employeeCsv = new EmployeeCsv("123,Piotr, Wozniakowsky, wozniakowsky@mail.com");


        return employees;
    }
}
