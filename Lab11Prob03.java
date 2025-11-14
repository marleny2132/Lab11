/**
 * File: Lab11Prob03.java
 * Class: CSCI 1302
 * Author: Marleny Olan-Sanchez
 * Created on: November 14, 2025
 * Description: Read binary data, create Person objects, sort, write serialized objects
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Lab11Prob03 {
    public static void main(String[] args) {
        DataInputStream input = null;
        DataOutputStream stringOutput = null;
        ObjectOutputStream objectOutput = null;
        ArrayList<Person> peopleList = new ArrayList<>();
        
        try {
            // Open input file for reading binary data
            input = new DataInputStream(
                new FileInputStream("src/person.dat"));
            
            System.out.println("Reading from person.dat and creating Person objects...\n");
            
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
            // Expected exception when end of file is reached
            System.out.println("Finished reading " + peopleList.size() + " person records.");
            
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
        System.out.println("\nSorted by salary (highest to lowest).\n");
        
        // Write sorted data as strings (from Problem 2)
        try {
            stringOutput = new DataOutputStream(
                new FileOutputStream("src/people-salary-sorted.dat"));
            
            // Write each Person's toString() output to file
            for (Person person : peopleList) {
                String personInfo = person.toString();
                stringOutput.writeUTF(personInfo);
            }
            
            System.out.println("String data written to people-salary-sorted.dat");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot create string output file - " + e.getMessage());
            
        } catch (IOException e) {
            System.out.println("Error writing string file: " + e.getMessage());
            
        } finally {
            // Close string output stream
            try {
                if (stringOutput != null) {
                    stringOutput.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing string output file: " + e.getMessage());
            }
        }
        
        // Write sorted data as serialized objects (NEW for Problem 3)
        try {
            objectOutput = new ObjectOutputStream(
                new FileOutputStream("src/people-salary-sorted-objects.dat"));
            
            // Write each Person object to file
            for (Person person : peopleList) {
                objectOutput.writeObject(person);
                
                // Display to console for verification
                System.out.println(person);
                System.out.println("---");
            }
            
            System.out.println("\nSerialized objects written to people-salary-sorted-objects.dat");
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot create object output file - " + e.getMessage());
            
        } catch (IOException e) {
            System.out.println("Error writing object file: " + e.getMessage());
            
        } finally {
            // Close object output stream
            try {
                if (objectOutput != null) {
                    objectOutput.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing object output file: " + e.getMessage());
            }
        }
    }
}