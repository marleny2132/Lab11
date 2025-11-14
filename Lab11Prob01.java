/**
 * File: Lab11Prob01.java
 * Class: CSCI 1302
 * Author: Marleny Olan-Sanchez
 * Created on: November 14, 2025
 * Description: Read binary data from person.dat and create an exact copy
 */

import java.io.*;

public class Lab11Prob01 {
    public static void main(String[] args) {
        DataInputStream input = null;
        DataOutputStream output = null;
        
        try {
            // Open input file for reading binary data
            input = new DataInputStream(
                new FileInputStream("src/people.dat"));
            
            // Open output file for writing binary data copy
            output = new DataOutputStream(
                new FileOutputStream("src/people-copy.dat"));
            
            // Read and display all records until EOF
            while (true) {
                // Read each field in order
                int age = input.readInt();
                String name = input.readUTF();
                String address = input.readUTF();
                int zipCode = input.readInt();
                double salary = input.readDouble();
                
                // Display to console
                System.out.printf("%d %s %s %d %.2f%n", age, name, address, zipCode, salary);
                
                // Write exact copy to output file
                output.writeInt(age);
                output.writeUTF(name);
                output.writeUTF(address);
                output.writeInt(zipCode);
                output.writeDouble(salary);
            }
            
        } catch (EOFException e) {
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
            
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
            
        } finally {
            // Close both streams
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing files: " + e.getMessage());
            }
        }
    }
}