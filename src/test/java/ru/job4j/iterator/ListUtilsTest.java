package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfEven() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ListUtils.removeIf(input, num -> num % 2 == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfNegative() {
        input = new ArrayList<>(Arrays.asList(1, -2, -3, -4, 5, 8));
        ListUtils.removeIf(input, num -> num < 0);
        assertThat(input).hasSize(3).containsSequence(1, 5, 8);
    }

    @Test
    void whenReplaceIfEven() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.replaceIf(input, num -> num % 2 == 0, 1000);
        assertThat(input).hasSize(6).containsSequence(1, 1000, 3, 1000, 5, 1000);
    }

    @Test
    void whenReplaceIfBiggerFive() {
        input = new ArrayList<>(Arrays.asList(1, 2, 8, 4, 5, 6));
        ListUtils.replaceIf(input, num -> num > 5, 9999);
        assertThat(input).hasSize(6).containsSequence(1, 2, 9999, 4, 5, 9999);
    }

    @Test
    void whenRemoveAllInOrder() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> elements = new ArrayList<>(Arrays.asList(3, 5, 7, 9));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(5).containsSequence(1, 2, 4, 6, 8);
    }

    @Test
    void whenRemoveAllWithoutOrder() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> elements = new ArrayList<>(Arrays.asList(7, 1, 5, 6, 4));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(4).containsSequence(2, 3, 8, 9);
    }

}