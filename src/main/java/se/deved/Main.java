package se.deved;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Lambdas
        // Streams
        // Generics
        // Optionals

        // LAMBDAS ----
        // Anonyma & värde
        // Capturing
        Runnable myLambda = () -> {
            System.out.println("Hej!");
        };

        Predicate<Integer> predicate = (num) -> {
            return num % 2 == 0;
        };

        Predicate<Integer> predicate2 = (num) -> num % 2 == 0;

        myLambda.run();

        MyLambda myLambda2 = new MyLambda();
        myLambda2.run();

        MyLambdaType lambda2 = (a, b) -> {
            return a + b;
        };

        // STREAMS ----
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(8);
        list.add(7);

        // Manuellt
        List<Integer> onlyEvenNumbers = new ArrayList<>();
        for (int num : list) {
            if (num % 2 == 0) {
                onlyEvenNumbers.add(num);
            }
        }

        // Med streams
        List<Integer> onlyEvenNumbers2 = list.stream()
                .filter((num) -> num % 2 == 0)
                .toList();

        double random = Math.random();
        List<Integer> randomNumbers = list.stream()
                .filter((num) -> random > 0.5)
                .sorted()
                .skip(1)
                .limit(3)
                .toList();

        List<Person> people = new ArrayList<>();
        people.add(new Person("Ironman", "tony@stark.com", 4.5, 30));
        people.add(new Person("Superman", "clark@kent.com", 2.5, 56));
        people.add(new Person("Batman", "bruce@wayne.com", 6.4, 34));
        people.add(new Person("Black Widow", "natasha@romanoff.com", 4.2, 20));

        Person tallest = getTallestPerson(people);
        System.out.println("Tallest person: " + tallest.name);

        int sumOfAges = sumOfAges(people);
        System.out.println(sumOfAges);
    }

    public static class MyLambda implements Runnable {
        @Override
        public void run() {
            System.out.println("Hej!");
        }
    }

    public static Person getTallestPerson(List<Person> list) {
        return list.stream()
                .max((personA, personB) -> (int) (personA.height - personB.height))
                .get();
    }

    public static List<Person> getPeopleWithCInTheirName(List<Person> list) {
        return list.stream()
                .filter((person) -> person.name.contains("c"))
                // .filter((person) -> person.birthDate.compareTo(new Date()) > 0)
                .toList();
    }

    public static int sumOfAges(List<Person> list) {
        return list.stream()
                .mapToInt((person) -> person.age)
                .sum();
    }
}