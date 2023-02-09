import com.philf.CodeToRun;
import com.philf.Department;
import com.philf.Employee;
import com.philf.UpperConcat;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G53", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29",
                "071");

        List<String> gNumbers = new ArrayList<>();

//        someBingoNumbers.forEach(number -> {
//            if(number.toUpperCase().startsWith("G")) {
//                gNumbers.add(number);
//                //System.out.println(number);
//            }
//        });
//
//        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
//        gNumbers.forEach((String s) -> System.out.println(s));

        // Using streams to do the same as above.  All in one statement instead of two foreach statements.
        // Note: Def of streams per Oracle - A sequence of elements supporting and parallel aggregate operations.
        // Basically, a stream is a set of object references.  The steam() (in Java collections package) method
        // creates a stream from a collection. Each object reference corresponds to a object in the collection.
        // The ordering of the object reference matches the ordering in the collection.
        // The "::" notation is a method reference.

        someBingoNumbers
                .stream() // A stream that contains all the items in the someBingoNumbers list, in the same order
                .map(String::toUpperCase) //A stream that contains all the bingo numbers in uppercased. Same as String upperString = toUpperCase(myString)
                .filter(s->s.startsWith("G"))  //A stream containing all the items beginning with "G".
                .sorted() //A stream containing the sorted items.
                .forEach(System.out::println); //The chain ends.  The terminal operation - returns either a void or a non-terminal result.

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "071");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I17", "071");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println("-------------------------------------------------------");
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());

        System.out.println("****************************************************");

        Employee john = new Employee("John Doe", 30);
        Employee jain = new Employee("Jain Doe", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snowWhite = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jain);
        hr.addEmployee(jack);
        hr.addEmployee(snowWhite);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------------");

//        List<String> sortedGNumbers = someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("G"))
//                .sorted()
//                .collect(Collectors.toList());

        List<String> sortedGNumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);


        for(String s : sortedGNumbers) {
            System.out.println(s);
        }


        //Grouping Example
        Map<Integer, List<Employee>> groupedByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));

        //Reduce example
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        //Example of streams when they are lazzly evaluated.
        // Meaning nothing happens until there is a terminal operation.
        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })
                .count();

        /*
        Notes:
        Different ways to write lambda expressions:
        1) Specified the types of parameters vs letting the compiler infer them.
        2) Used a return statement with curly braces for one-statement lambda expressions
        vs not using a return because it's implied (and hence not requiring curly braces).
        3) Used lambda expressions that contain one statement vs Lambda expressions that have
        more than one statement.
        4) Using parenthesis when a lambda expression only has one argument vs not using parenthesis,
        since they're optional when there's only one argument.

        If you look at the four variations, the two alternatives offer the choice between verbosity vs conciseness,
        which in turn, often comes down to the choice between readability and conciseness.  Not all the time.
        Short lambda expressions are usually readable no matter how concise they are.  But when striving for
        conciseness, we can sometimes write lambda expressions that are difficult to decipher we've left out too
        much information.  Remember best practices are guidelines and NOT rules.  Readable? Concise?  Just be
        consistent.
         */



    }

}
