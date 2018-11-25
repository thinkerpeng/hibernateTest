package com.pwx;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String>  personList = Arrays.asList("11", "22", "33");
        personList.forEach(person ->
        {
            System.out.println(person);
            System.out.println(person);
        });
    }
}
