package pl.etroya.design.adapter;

import java.util.List;

public class AdapterSample
{
    public static void main(String[] args){
        EmployeeClient client = new EmployeeClient();
        List<Employee> employees = client.getEmployeeList();
        System.out.println(employees);
    }
}
