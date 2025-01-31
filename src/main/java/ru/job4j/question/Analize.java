package ru.job4j.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changedUsers = 0;
        int deletedUsers = 0;

        Map<Integer, String> map = new HashMap<>();
        for (User user : current) {
            map.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            String value = map.get(user.getId());
            if (value != null && !value.equals(user.getName())) {
                changedUsers++;
            } else if (value == null) {
                deletedUsers++;
            }
            map.remove(user.getId());
        }

        return new Info(
                map.size(),
                changedUsers,
                deletedUsers);
    }
}
