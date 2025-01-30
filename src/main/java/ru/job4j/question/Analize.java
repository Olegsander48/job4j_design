package ru.job4j.question;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Supplier<Integer> addUsers = () -> {
            Set<User> previousSet = new HashSet<>(previous);
            previousSet.addAll(current);
            return previousSet.stream()
                    .collect(Collectors.groupingBy(User::getId))
                    .values()
                    .size();
        };
        Supplier<Integer> changedUsers = () -> {
            Set<User> previousSet = new HashSet<>(previous);
            previousSet.addAll(current);
            return (int) previousSet.stream()
                    .collect(Collectors.groupingBy(User::getId))
                    .values()
                    .stream()
                    .filter(list -> list.size() >= 2)
                    .count();
        };
        Supplier<Integer> deletedUsers = () -> {
            List<User> previousList = new ArrayList<>(previous);
            previousList.addAll(current);
            previousList = previousList.stream()
                    .collect(Collectors.groupingBy(User::getId))
                    .values()
                    .stream()
                    .filter(list -> list.size() < 2)
                    .map(list -> list.get(0))
                    .collect(Collectors.toList());
            previousList.removeAll(current);
            return previousList.size();
        };
        return new Info(
                addUsers.get() - previous.size(),
                changedUsers.get(),
                deletedUsers.get());
    }
}
