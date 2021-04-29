package functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExampleMultipleLines {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Printing from the Runnable");
            System.out.println("Printing from the Runnable line2");
        }).start();


        Employee john = new Employee("John Daada", 20);
        Employee miriam = new Employee("Miriam Daada", 18);
        Employee bob = new Employee("Bob Daada", 5);
        Employee danny = new Employee("Danny Daada", 1);


        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(miriam);
        employees.add(bob);
        employees.add(danny);

//            Collections.sort(employees, new Comparator<Employee>() {
//                @Override
//                public int compare(Employee employee1, Employee employee2) {
//                    return employee1.getName().compareTo(employee2.getName());
//                }
//            });

        Collections.sort(employees, (Employee employee1, Employee employee2) ->
                employee1.getName().compareTo(employee2.getName()));

        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }
    }

}

//public final static String doStringStaff(UpperConcat uc, String s1, String s2){
//    return uc.upperAndConcat((s1, s2));
//}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);

}
