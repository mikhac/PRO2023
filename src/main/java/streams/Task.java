package streams;

import streams.model.Human;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

// Code from https://github.com/vfarcic/java-8-exercises

public class Task {

    /**
     * EASY
     */

    public static List<String> toUpperCaseOldJava(List<String> collection) {
        List<String> newCollection = new ArrayList<>();
        for (String element : collection) {
            newCollection.add(element.toUpperCase());
        }
        return newCollection;
    }

    public static List<String> toUpperCase(List<String> collection) {
        return collection.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static List<String> transformOldJava(List<String> collection) {
        List<String> newCollection = new ArrayList<>();
        for (String element : collection) {
            if (element.length() < 4) {
                newCollection.add(element);
            }
        }
        return newCollection;
    }

    public static List<String> transform(List<String> collection) {
        return collection.stream()
                .filter(x -> x.length() < 4)
                .collect(Collectors.toList());
    }

    /**
     * MEDIUM
     */

    public static Map<String, Human> createMapOldJava(List<Human> collection) {
        Map<String, Human> people = new HashMap<>();
        for (Human element : collection) {
            people.put(element.getName(), element);
        }
        return people;
    }

    public static Map<String, Human> createMap(List<Human> collection) {
        return collection.stream()
                .collect(Collectors.toMap(Human::getName, Function.identity()));
    }


    public static Human getOldestPersonOldJava(List<Human> people) {
        Human oldestHuman = new Human("", 0);
        for (Human human : people) {
            if (human.getAge() > oldestHuman.getAge()) {
                oldestHuman = human;
            }
        }
        return oldestHuman;
    }

    public static Human getOldestPerson(List<Human> people) {
        return people.stream()
                .sorted(Comparator.comparing(Human::getAge).reversed())
                .limit(1)
                .findAny()
                .get();
    }

    /**
     * HARD
     */


    public static Map<Boolean, List<Human>> partitionAdultsOldJava(List<Human> people) {
        Map<Boolean, List<Human>> map = new HashMap<>();
        map.put(true, new ArrayList<>());
        map.put(false, new ArrayList<>());
        for (Human human : people) {
            map.get(human.getAge() >= 18).add(human);
        }
        return map;
    }

    // use partitionBy
    public static Map<Boolean, List<Human>> partitionAdults(List<Human> people) {
        return people.stream()
                .collect(partitioningBy(person -> person.getAge() >= 18));
    }

    public static List<String> transformListOldJava(List<List<String>> collection) {
        List<String> newCollection = new ArrayList<>();
        for (List<String> subCollection : collection) {
            for (String value : subCollection) {
                newCollection.add(value);
            }
        }
        return newCollection;
    }

    //use flatMap
    public static List<String> transformList(List<List<String>> collection) {
        return collection.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}