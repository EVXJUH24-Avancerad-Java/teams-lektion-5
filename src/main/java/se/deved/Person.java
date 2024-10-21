package se.deved;

import java.util.Date;

public class Person {

    public String name;
    public String email;
    public double height;
    public int age;
    public Date birthDate;

    public Person(String name, String email, double height, int age) {
        this.name = name;
        this.email = email;
        this.height = height;
        this.age = age;
    }
}
