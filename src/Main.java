import com.philf.CodeToRun;
import com.philf.Employee;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //new Thread(new CodeToRun()).start();

        //second way to create a new Thread - using an anonymous class
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("printing from the Runnable");
//            }
//        }).start();

        //using lambdas -- Note: Lambdas are a feature in Java release 8+
        //Lambda expressions have 3 parts:
        // 1. Argument list
        // 2. The arrow token
        // 3. The body.
        // Note: () -> System.out.println("Printing from Lambda") is an empty argument list.
        // Note: the --> is the arrow token.
        // Note: The body is the code we want to run. Which in the eample below is System.out.println("Printing from Lambda")
        new Thread(() -> {
            System.out.println("Printing from Runnable");
            System.out.println("Line 2");
            System.out.format("This is line %d\n",3);
        }).start();

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Phil Fernandez", 49);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(john);
        employeeList.add(tim);
        employeeList.add(jack);
        employeeList.add(snow);

        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });

        for(Employee employee : employeeList) {
            System.out.println(employee.getName());
        }


    }
}