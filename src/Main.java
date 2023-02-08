import com.philf.CodeToRun;
import com.philf.Employee;
import com.philf.UpperConcat;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Phil Fernandez", 49);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(john);
        employeeList.add(tim);
        employeeList.add(jack);
        employeeList.add(snow);

        employeeList.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

//        for(Employee employee: employeeList) {
//            System.out.println(employee.getName());
//            new Thread(() -> System.out.println(employee.getAge())).start();
//        }





    }


    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1,s2);

    }

}

//Anonymous class.
class AnotherClass {
    public String doSomething() {
        int i = 0; //This value can't as it's final.

        UpperConcat uc = (s1, s2) -> {
            System.out.println("The Lambda expression's class is: " + getClass().getSimpleName());
            System.out.println("i in the lambda expression = " + i);
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;

        };

//        int j = 0;
//        {
//            String s1;
//            String s2;
//            System.out.println("The Lambda expression's class is: " + getClass().getSimpleName());
//            System.out.println("i in the lambda expression = " + i);
//
//            //S1 and S2 are not available outside of the lambda.
//            String result = s1.toUpperCase() + s2.toUpperCase();
//            return result;
//
//
//        }

        {
            System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
            return Main.doStringStuff(uc, "String1", "String2");
        }

    }
//        System.out.println("The AnotherClass class's is: " + getClass().getSimpleName());
//        return Main.doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                System.out.println("The anonymous class's name is: " + getClass().getSimpleName());
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        }, "String1", "String2");
//    }

    public void printValue() {
        int number = 25;
        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The value is " + number);
        };

        new Thread(r).start();
    }
}
