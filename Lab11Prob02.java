/**
 * File: Lab11Prob02.java
 * Class: CSCI 1302
 * Author: Marleny Olan-Sanchez
 * Created on: November 14, 2025
 * Description: Read binary data, create Person objects, sort by salary, write to file
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Lab11Prob02 {
    public static void main(String[] args) {
        DataInputStream input = null;
        DataOutputStream output = null;
        ArrayList<Person> peopleList = new ArrayList<>();
        
        try {
            // Open input file for reading binary data
            input = new DataInputStream(
                new FileInputStream("src/people.dat"));
            
            // Read all records and create Person objects
            while (true) {
                // Read each field in order
                int age = input.readInt();
                String name = input.readUTF();
                String address = input.readUTF();
                int zipCode = input.readInt();
                double salary = input.readDouble();
                
                // Create Person object and add to ArrayList
                Person person = new Person(age, name, address, zipCode, salary);
                peopleList.add(person);
            }
            
        } catch (EOFException e) {
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
            return;
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
            
        } finally {
            // Close input stream
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing input file: " + e.getMessage());
            }
        }
        
        // Sort the ArrayList by salary (descending order)
        Collections.sort(peopleList);
        
        // Write sorted data to output file
        try {
            output = new DataOutputStream(
                new FileOutputStream("src/people-salary-sorted.dat"));
            
            // Write each Person's toString() output to file
            for (Person person : peopleList) {
                String personInfo = person.toString();
                output.writeUTF(personInfo);
                
                // Display to console
                System.out.println(personInfo);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot create output file - " + e.getMessage());
            
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            
        } finally {
            // Close output stream
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing output file: " + e.getMessage());
            }
        }
    }
}