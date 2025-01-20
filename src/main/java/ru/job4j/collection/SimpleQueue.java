package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();

    int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T deletedItem = input.pop();
        size--;
        return deletedItem;
    }

    public void push(T value) {
        for (int i = 0; i < size; i++) {
            output.push(input.pop());
        }
        output.push(value);
        size++;
        for (int i = 0; i < size; i++) {
            input.push(output.pop());
        }
    }
}
