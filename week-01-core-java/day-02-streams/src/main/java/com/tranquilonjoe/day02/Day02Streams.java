package com.tranquilonjoe.day02;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day02Streams {

    public static List<String> getJavaDevelopers(List<Person> people) {
        return people.stream()
                .filter(person -> person.getSkills().stream().anyMatch(s -> s.equalsIgnoreCase("java")))
                .map(Person::getName)
                .toList();
    }

    public static Map<String, Long> countByDepartment(List<Person> people) {
        return people.stream().collect(
                Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.counting()
                )
        );
    }

    public static Map<String, Set<String>> getSkillsByDepartment(
            List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(
                                Person::getDepartment,
                                Collectors.flatMapping(
                                        person -> person.getSkills().stream(),
                                        Collectors.toSet()
                                )
                        )
                );
    }

    public static Optional<String> findFirstNonRepeatedName(
            List<Person> people) {
        Map<String, Long> countNames = people.stream()
                .map(Person::getName)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                name -> 1L,
                                Long::sum,
                                LinkedHashMap::new
                        )
                );
        return countNames.entrySet().stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public static Map<Boolean, List<String>> partitionByJavaSkill(
            List<Person> people) {
        return people.stream()
                .collect(Collectors.partitioningBy(
                        person -> person.getSkills().stream().anyMatch(s -> s.equalsIgnoreCase("java")),
                        Collectors.mapping(
                                Person::getName,
                                Collectors.toList()
                        )
                ));
    }

    public static Optional<Person> findOldestEmployee(
            List<Person> people) {
        return people.stream()
                .max(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Comparator.comparing(Person::getName).reversed()));
    }

    public static void main(String[] args) {
        Person person1 = new Person(1, "Nirmal", 38, "IT", "Bangalore", List.of("Java", "AWS", "Docker"));
        Person person2 = new Person(2, "Bob", 35, "IT", "Bangalore", List.of("Docker", "Python"));
        Person person3 = new Person(3, "David", 40, "IT", "Bangalore", List.of("Java", "Docker"));
        Person person4 = new Person(4, "Charlie", 38, "HR", "Kochi", List.of("Java", "Excel"));
        Person person5 = new Person(5, "Alice", 28, "HR", "Bangalore", List.of("Excel"));
        Person person6 = new Person(6, "Tango", 40, "IT", "Kochi", List.of("AWS", "Docker"));
        Person person7 = new Person(7, "Nirmal", 35, "HR", "Kochi", List.of("Java", "Excel"));
        Person person8 = new Person(8, "Bob", 40, "IT", "Kochi", List.of("Java", "Docker"));

        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);
        people.add(person6);
        people.add(person7);
        people.add(person8);

        System.out.println("Java developers: "
                + getJavaDevelopers(people));

        System.out.println("Department counts: "
                + countByDepartment(people));

        System.out.println("Skills by department: "
                + getSkillsByDepartment(people));

        System.out.println("First non-repeated name: "
                + findFirstNonRepeatedName(people));

        System.out.println("Java partition: "
                + partitionByJavaSkill(people));

        System.out.println("Oldest employee: "
                + findOldestEmployee(people));
    }
}
