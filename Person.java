/**
 * File: Person.java
 * Class: CSCI 1302
 * Author: Marleny Olan-Sanchez
 * Created on: November 14, 2025
 * Description: Person class with Comparable and Serializable interfaces
 */

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Person implements Comparable<Person>, Serializable {
    // Data members
    private int age;
    private String name;
    private String address;
    private int zipCode;
    private double salary;
    
    // Default constructor
    public Person() {
        this.age = 0;
        this.name = "";
        this.address = "";
        this.zipCode = 0;
        this.salary = 0.0;
    }
    
    // Parameterized constructor
    public Person(int age, String name, String address, int zipCode, double salary) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.salary = salary;
    }
    
    // Getters
    public int getAge() {
        return age;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public int getZipCode() {
        return zipCode;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Setters
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    // Compare by salary in DESCENDING order
    @Override
    public int compareTo(Person other) {
        // Descending order: other.salary compared to this.salary
        return Double.compare(other.salary, this.salary);
    }
    
    // toString with formatted currency
    @Override
    public String toString() {
        // Format salary with USD currency symbol, commas, and 2 decimal places
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedSalary = currencyFormat.format(salary);
        
        // Single line format: age name address zipcode salary
        return age + " " + name + " " + address + " " + zipCode + " " + formattedSalary;
    }
}