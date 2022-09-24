package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

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
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveEvenNumbers() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.removeIf(input, el -> el % 2 == 0);
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }

    @Test
    void whenReplaceEvenNumbers() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ListUtils.replaceIf(input, el -> el % 2 == 0, 1000);
        assertThat(input).hasSize(6).containsSequence(1, 1000, 3, 1000, 5, 1000);
    }

    @Test
    void whenRemoveDuplicateNumbersFromBothLists() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 5, 6, 7, 8));
        ListUtils.removeAll(input, Arrays.asList(3, 4, 5, 8));
        assertThat(input).hasSize(4).containsSequence(1, 2, 6, 7);
    }
}