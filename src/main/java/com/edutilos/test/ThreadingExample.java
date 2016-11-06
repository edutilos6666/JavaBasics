package com.edutilos.test;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ThreadingExample {
    public static void main(String[] args) {
        List<MyRunnable> runnables = Arrays.asList(
                new MyRunnable("Runnable1"),
                new MyRunnable("Runnable2"),
                new MyRunnable("Runnable3")
        );

        runnables.forEach(runnable -> {
           runnable.start();
        });
    }
}


class MyRunnable implements  Runnable {
  private String name;

    public MyRunnable(String name) {
        this.name = name;

    }

    public void start() {
        System.out.println("Starting thread " + name);
        Thread thread = new Thread(this, name);
        thread.start();
    }

    private double generateRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }
    @Override
    public void run() {
        for(int i=0 ; i< 5; ++i) {
            double rand = generateRandomDouble();
            System.out.println(name + " Generating random number: "+ rand);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
