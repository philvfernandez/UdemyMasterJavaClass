import com.philf.*;

import javax.crypto.spec.PSource;
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

        //Challenge 1
        String myString = "Let's split this up into an array";

        Runnable r = () -> {

        String[] parts = myString.split(" ");
        for (String part : parts) {
            System.out.println(part);
        }

        };

        new Thread(r).start();

        //Challenge 2 - write the following method as a lambda expression.
        /*
           public static String everySecondChar(String source) {
           StringBuilder returnVal = new StringBuilder();
           for(int i = 0; i < source.length(); i++) {
             if(i % 2 == 1) {
               returnVal.append(source.charAt(i));
             }
           }
           return returnVal.toString();
          }
         */

        //my solution
        EverySecondChar everySecondChar = (source) -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i = 0; i < source.length();i++) {
                if(i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }

            }
            return  returnVal.toString();
        };

        //Instructor's solution
        Function<String, String> everySecondChar2 = s -> {
            StringBuilder returnVal = new StringBuilder();
            for(int i = 0; i < s.length(); i++) {
                if(i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };

        //my solution -
        System.out.println(everySecondChar.everySecondChar("12345867890"));
        //Instructor's solution
        System.out.println(everySecondChar2.apply("12345867890"));

        //call for challenge #4
        String result = everySecondChar3(everySecondChar2, "1234567890");
        System.out.println(result);

        //solution for challenge #6
        Supplier<String> iLoveJava = () -> "I Love Java!!";

        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);

        //Challenge #9 - Write code to print the items in the list in sorted order and with the first letter in each
        // name upper-cased.  The name "harry" should be printed as "Harry" and should be printed after "Emily" and
        // before "Isla".  Use lambda expressions wherever possible.
        List<String> topNames2015 = Arrays.asList(
                "Amelia",
                "Oliva",
                "emily",
                "Isla",
                "Ava",
                "oliver",
                "Jack",
                "Charlie",
                "harry",
                "Jacob"
        );

        List<String> sortedNames = new ArrayList<>();

        //Solution for challenge #9
        /*topNames2015.forEach(name ->
                sortedNames.add(name.substring(0,1).toUpperCase() + name.substring(1)));
        sortedNames.sort((s1, s2) -> s1.compareTo(s2));
        sortedNames.forEach(s -> System.out.println(s)); */


        //Solution for Challenge #10 - Change the code so that it uses method references.  Remember that a
        //method reference looks like Class::MethodName
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Using method reference in Lambda");
        topNames2015.forEach(name ->
                sortedNames.add(name.substring(0,1).toUpperCase() + name.substring(1)));
        sortedNames.sort(String::compareTo);
        sortedNames.forEach(System.out::println);


        //Solution for challenge #11 - Now do the same thing (uppercase first letter, then sort and print the list)
        //using a stream and a chain of stream operations.
        System.out.println("***********************************************************************");
        System.out.println("Using stream operations");
        topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .sorted(String::compareTo)
                .forEach(System.out::println);

        //Solution for challenge #13 - Instead of printing out the sorted names, print out how many names
        //being with the letter 'A' instead
        //Two hints:
        //1) You'll have to modify the stream chain
        //2) You'll have to add another stream statement to print the number of items.
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Challege #12");
       long namesBegginingWithA = topNames2015
                .stream()
                .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println("Number of names beginning with A is: " + namesBegginingWithA);

        //Solution for challenge 13 - What will the following code print to the console
        //Answer: Nothing because this chain doesn't contain a terminal operation.
        /* System.out.println("**********************************************************************");
        System.out.println("Challenge #13");
        topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo); */

        //Solution for challenge 14 - Add a terminal operation to the code in challenge 13 so that the
        //peek call will execute.
        System.out.println("**********************************************************************");
        System.out.println("Challenge #14");
        topNames2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());






    }

    //Challenge #4/5
    public static String everySecondChar3(Function<String, String> func, String source) {
        return func.apply(source);
    }

}
