package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            arrayExpansion();
        }
        modCount++;
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T previousValue = get(index);
        container[index] = newValue;
        return previousValue;
    }

    @Override
    public T remove(int index) {
        T removedValue = get(index);
        modCount++;
        size--;
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void arrayExpansion() {
        int newLength = size;
        if (newLength == 0) {
            newLength = 1;
        }
        container = Arrays.copyOf(container, newLength * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }

        };
    }
}