package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count >= table.length * LOAD_FACTOR) {
            expand();
        }
        int index = getIndexForKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            modCount++;
            count++;
        }
        return result;
    }

    @Override
    public V get(K key) {
        int index = getIndexForKey(key);
        V value = null;
        if (table[index] != null && checkForEquality(index, key)) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndexForKey(key);
        boolean remove = false;
        if (table[index] != null && checkForEquality(index, key)) {
            table[index] = null;
            remove = true;
            modCount++;
            count--;
        }
        return remove;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int getIndexForKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                int index = hash(Objects.hashCode(table[i].key)) & (newTable.length - 1);
                newTable[index] = table[i];
            }
        }
        table = newTable;
    }

    private boolean checkForEquality(int keyInTable, K key) {
        return Objects.hashCode(table[keyInTable].key) == Objects.hashCode(key)
                && Objects.equals(table[keyInTable].key, key);
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<String, Integer> map = new NonCollisionMap<>();
        System.out.println(map.hash(0));
        System.out.println(map.hash(65535));
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(0));
        System.out.println(map.indexFor(7));
        System.out.println(map.indexFor(8));
        System.out.println(map.indexFor(map.hash(Objects.hashCode(null))));
    }
}
