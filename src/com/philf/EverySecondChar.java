package com.philf;

/*
This interface can be mapped to a lambda expression to it because:
- The interface has to bew a functional interface.  Meaning it can have only a single method that must be
  implemented.
- A functional interface can contain more than one method, but all the methods but one must have default
  implementations.
- The docs for an interface will likely state whether it's a functional interface or not.
 */
public interface EverySecondChar {
    public String everySecondChar(String s1);
}
