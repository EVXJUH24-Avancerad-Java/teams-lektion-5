package se.deved;

import java.util.*;
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

        // Iterators (alternativ till for-loop)
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            int num = iter.next();
            System.out.println(num);
        }

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

        Optional<Person> tallest = getTallestPerson(people);
        if (tallest.isPresent()) {
            System.out.println("Tallest person: " + tallest.get().name);
        }

        Optional<Person> tallest2 = getTallestPersonManually(people);
        String name = tallest2.map(value -> value.name).orElse("No tallest person exists");
        System.out.println(name);

        // Optional.of(5);
        // Optional.empty();

        int sumOfAges = sumOfAges(people);
        System.out.println(sumOfAges);

        // GENERICS ----
        int i = 5;
        String s = "";
        // Type type = int;
        // Type type = Random;
        // Type type = Person;

        MyList<String> myStringList = new MyList<>();
        MyList<Integer> myIntList = new MyList<>();
        MyList<Person> myPersonList = new MyList<>();

        Main.<String>print("Hej!");
        print(5);
        print(true);
        print(people.get(0));
        print(myLambda);
    }

    public static <T extends Runnable> void print(T value) {
        System.out.println(value);
        value.run();
    }

    public static class MyLambda implements Runnable {
        @Override
        public void run() {
            System.out.println("Hej!");
        }
    }

    public static Optional<Person> getTallestPerson(List<Person> list) {
        return list.stream()
                .max((personA, personB) -> (int) (personA.height - personB.height));
    }

    public static Optional<Person> getTallestPersonManually(List<Person> list) {
        if (list.isEmpty())
            return Optional.empty();

        Person tallest = list.getFirst();
        for (Person person : list) {
            if (person.height > tallest.height) {
                tallest = person;
            }
        }

        return Optional.of(tallest);
    }

    public static List<Person> getPeopleWithCInTheirName(List<Person> list) {
        return list.stream()
                .filter((person) -> person.name.contains("c") || person.name.contains("C"))
                // .filter((person) -> person.birthDate.compareTo(new Date()) > 0)
                .toList();
    }

    public static List<Person> getPeopleAboveTwentyWithoutMail(List<Person> list) {
        return list.stream()
                .filter((person) -> person.age > 20)
                .filter((person) -> person.email == null)
                .sorted(Comparator.comparing(person -> person.name))
                .toList();
    }

    public static int sumOfAges(List<Person> list) {
        return list.stream()
                .mapToInt((person) -> person.age)
                .sum();
    }
}