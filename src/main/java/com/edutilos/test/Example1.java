package com.edutilos.test;

/**
 * Created by edutilos on 05/11/2016.
 */
public class Example1 {
    public static void main(String[] args) {
        Person p1, p2;
        p1 = new Person("foo", 10 , 100.0);
        p2 = new Person("bar", 20 , 200.0);
        System.out.println(p1);
        System.out.println(p2);
    }
}

class Person {
    private String name;
    private int age ;
    private double wage;

    public Person(String name, int age, double wage) {
        this.name = name;
        this.age = age;
        this.wage = wage;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                '}';
    }
}

