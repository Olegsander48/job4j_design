package ru.job4j.map;

import com.sun.jdi.Value;

import java.security.Key;
import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int index = key != null ? indexFor(hash(key.hashCode())) : 0;
        if (count < capacity && table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        expand();
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= capacity * LOAD_FACTOR) {
            MapEntry<K, V>[] oldTable = table;
            table = new MapEntry[capacity * 2];
            capacity = table.length;
            for (MapEntry<K, V> map : oldTable) {
                if (map != null) {
                    put(map.key, map.value);
                    count--;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = key != null ? indexFor(hash(key.hashCode())) : 0;
        MapEntry<K, V> entry = table[index];
        if (index < capacity && entry != null) {
            if (key != null && entry.key != null && entry.key.hashCode() == key.hashCode() && entry.key.equals(key)) {
                result = entry.value;
            } else if (key == null && entry.key == null) {
                result = entry.value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = key != null ? indexFor(hash(key.hashCode())) : 0;
        MapEntry<K, V> entry = table[index];
        if (table[index] != null) {
            if (key != null && entry.key != null && entry.key.hashCode() == key.hashCode() && entry.key.equals(key)) {
                table[index] = null;
                count--;
                modCount++;
                result = true;
            } else if (key == null && entry.key == null) {
                table[index] = null;
                count--;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int index = 0;
            int expectedModCount = modCount;
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

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}