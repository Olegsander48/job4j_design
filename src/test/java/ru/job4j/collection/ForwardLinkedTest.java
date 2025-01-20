package ru.job4j.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ForwardLinkedTest {

    private ForwardLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new ForwardLinked<>();
        list.addLast(1);
        list.addLast(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.addLast(3);
        list.addLast(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void checkAddLast() {
        assertThat(list).containsExactly(1, 2);
        list.addLast(3);
        assertThat(list).containsExactly(1, 2, 3);
    }

    @Test
    void whenAddLastAndGet() {
        list.addLast(3);
        list.addLast(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenGetNegateIndexThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddLastAndDeleteFirstThenOk() {
        assertThat(list).containsExactly(1, 2);
        list.addLast(3);
        assertThat(list).containsExactly(1, 2, 3);
        assertThat(list.deleteFirst()).isEqualTo(1);
        assertThat(list).containsExactly(2, 3);
        assertThat(list.deleteFirst()).isEqualTo(2);
        assertThat(list).containsExactly(3);
    }

    @Test
    void whenDeleteFirstFromEmptyListThenException() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        assertThat(list).isEmpty();
        assertThatThrownBy(list::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenAddLastIterHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenHasIteratorAndAddLastThenHasNextExceptionThrown() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.addLast(3);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenHasIteratorAndAddLastThenNextExceptionThrown() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.addLast(3);
        assertThatThrownBy(iterator::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenAddLastIterNextOne() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isFalse();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenAddLastIterMultiHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddLastIterNextOneNextTwo() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }

    @Test
    void whenAddLastAndDeleteLastThenOk() {
        assertThat(list).containsExactly(1, 2);
        list.addLast(3);
        assertThat(list).containsExactly(1, 2, 3);
        assertThat(list.deleteLast()).isEqualTo(3);
        assertThat(list).containsExactly(1, 2);
        assertThat(list.deleteLast()).isEqualTo(2);
        assertThat(list).containsExactly(1);
    }
}