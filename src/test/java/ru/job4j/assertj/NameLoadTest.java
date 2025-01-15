package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyStringArray() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty()
                .contains("is empty");
    }

    @Test
    void checkStringWithoutSeparator() {
        NameLoad nameLoad = new NameLoad();
        String parseString = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(parseString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(parseString)
                .hasMessageContaining("the symbol '='");
    }

    @Test
    void checkStringWithoutKey() {
        NameLoad nameLoad = new NameLoad();
        String parseString = "=value";
        assertThatThrownBy(() -> nameLoad.parse(parseString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(parseString)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void checkStringWithoutValue() {
        NameLoad nameLoad = new NameLoad();
        String parseString = "key=";
        assertThatThrownBy(() -> nameLoad.parse(parseString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(parseString)
                .hasMessageContaining("not contain a value");
    }

}