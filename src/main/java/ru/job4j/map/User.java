package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(Locale.CANADA);
        User user1 = new User("Sasha", 2, calendar);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;

        User user2 = new User("Sasha", 2, calendar);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(user1.equals(user2));
        System.out.println("User 1 hashcode: " + hashCode1 + ", hash: " + hash1 + ", bucket" + bucket1);
        System.out.println("User 2 hashcode: " + hashCode2 + ", hash: " + hash2 + ", bucket" + bucket2);
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println("User: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
