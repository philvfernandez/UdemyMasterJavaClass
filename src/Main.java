import com.philf.CodeToRun;
import com.philf.Employee;
import com.philf.UpperConcat;

import java.util.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Phil Fernandez", 19);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 35);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(john);
        employeeList.add(tim);
        employeeList.add(jack);
        employeeList.add(snow);
        employeeList.add(red);
        employeeList.add(charming);


//        printEmployeesByAge(employeeList, "Employees Over 30", employee -> employee.getAge() > 30);
//        printEmployeesByAge(employeeList, "Employees 30 and Under", employee -> employee.getAge() < 30);
//        printEmployeesByAge(employeeList, "Employees younger than 25", new Predicate<Employee>() {
//            @Override
//            public boolean test(Employee employee) {
//                return employee.getAge() < 25;
//
//            }
//        });
//        IntPredicate greaterThan15 = i -> i > 15;
//        IntPredicate lessThan100 = i -> i < 100;
//
//        System.out.println(greaterThan15.test(10));
//        int a = 20;
//        System.out.println(greaterThan15.test(a + 5));
//        System.out.println(greaterThan15.and(lessThan100).test(50));
//        System.out.println(greaterThan15.and(lessThan100).test(15));
//
//        Random random = new Random();
//        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
//
//        for(int i=0;i<10;i++) {
//            System.out.println(randomSupplier.get());
//        }
//
//        employeeList.forEach(employee -> {
//            String lastName = employee.getName().substring(employee.getName().indexOf(' ') + 1);
//            System.out.println("Last Name is: " + lastName);
//        });

        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        String lastName = getLastName.apply(employeeList.get(1));
        System.out.println(lastName);

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        Random random1 = new Random();
        for(Employee employee : employeeList) {
            if(random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }

        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
        Function chainedFunc = upperCase.andThen(firstName);
        System.out.println(chainedFunc.apply(employeeList.get(0)));

        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employeeList.get(0));
        System.out.println(concatAge.apply(upperName, employeeList.get(0)));

        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10));

        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello World!!");

    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText, Predicate<Employee> ageCondition) {
        System.out.println(ageText);
        System.out.println("====================");
        for (Employee employee : employees) {
            if(ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}
