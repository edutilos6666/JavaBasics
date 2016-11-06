package com.edutilos.test;

import java.io.Serializable;

/**
 * Created by edutilos on 05/11/2016.
 */
public class Worker1 implements Serializable {
    private String name ;
    private int age;
    private double wage ;

    public Worker1(String name, int age, double wage) {
        this.name = name;
        this.age = age;
        this.wage = wage;
    }

    public Worker1() {
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
        return "Worker1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                '}';
    }
}
