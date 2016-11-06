package com.edutilos.test;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTime {
    public static void main(String[] args) {
       m5();
    }





    private static void m5() {
        List<Worker1> list = Arrays.asList(
                new Worker1("foo", 10, 100.0),
                new Worker1("bar", 20 , 200.0),
                new Worker1("bim", 30 , 300.0)
        );


        String fileName = "list.ser";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch(IOException ex) {
            ex.printStackTrace();
        }



        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Worker1> newList = (List<Worker1>) ois.readObject();
            newList.forEach(w-> System.out.println(w));
        } catch(IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void m4() {
        String pattern = "(.*)(\\d+)(.*)";
        String line = "foo10 bar bim20 deko30beko";

        Pattern p = Pattern.compile(pattern);
        Matcher  matcher = p.matcher(line);
        while(matcher.find()) {
            String g1 = matcher.group(0);
            String g2 = matcher.group(1);
            String g3 = matcher.group(2);
            System.out.println("group1 = " + g1);
            System.out.println("group2 = "+ g2);
            System.out.println("group3 = "+ g3);
        }
    }

    private static void m3() {
        String fileName = "foo.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

             List<String> sentences = new ArrayList<>();
            String[] ss  = {
               "my name is foo",
                    "my age is 10",
                    "my wage is 100"
            };

            for(String s: ss)
                sentences.add(s) ;
            sentences.forEach(s -> {
                try {
                    writer.append(s);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


        //reading
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line ;
            line = reader.readLine();
            while(line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void m1() {
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = LocalDate.of(1991, 10 , 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate d3 = LocalDate.parse("1991/10/10", formatter);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);


        int day = d1.get(ChronoField.DAY_OF_MONTH);
        int month = d1.get(ChronoField.MONTH_OF_YEAR);
        int year = d1.get(ChronoField.YEAR);
        System.out.println(String.format("%d %d %d", day, month , year));
        day = d2.getDayOfMonth();
        month = d2.getMonth().getValue();
        year = d2.getYear();
        System.out.println(String.format("%d %d %d", day, month , year));
    }


    private static void m2() {
     //ArrayList
        List<Worker1> l1 = new ArrayList<>();
        Worker1 w1, w2, w3 ;
        w1 = new Worker1("foo", 10 ,100.0);
        w2 = new Worker1("bar", 20 ,200.0);
        w3 = new Worker1("bim", 30 , 300.0);
        Worker1[] wl = {w1, w2, w3};
        for(Worker1 w: wl)
            l1.add(w);


        l1.forEach(w -> {
            System.out.println(w);
        });



        //LinkedList
        List<Worker1> l2 = new LinkedList<>();
        for(Worker1 w: wl)
            l2.add(w);
        l2.forEach(w-> {
            System.out.println(w);
        });



        //HashMap
        Map<Integer, Worker1> m1 = new HashMap<>();
        for(int i= 0 ; i< wl.length; ++i) {
            m1.put(i, wl[i]);
        }


        m1.forEach((index, worker) -> {
            System.out.println(index + " and " + worker);
        });



        Set<Map.Entry<Integer, Worker1>> entrySet = m1.entrySet();
        entrySet.forEach(entry -> {
            int key = entry.getKey();
            Worker1 value = entry.getValue();
            System.out.println(key + " and "+ value);
        });
    }


}
