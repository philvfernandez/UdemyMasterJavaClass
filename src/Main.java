import com.philf.CodeToRun;
import com.philf.Employee;
import com.philf.UpperConcat;

import java.util.*;
import java.util.function.*;
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

    }

}
