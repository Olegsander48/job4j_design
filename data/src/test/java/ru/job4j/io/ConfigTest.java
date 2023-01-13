package ru.job4j.io;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Aleksandr");
    }

    @Test
    void whenOnlyCommentsAndBlanks() {
        String path = "./data/comments_and_blanks.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("PostgreSQL")).isEqualTo(null);
    }

    @Test
    void whenPairWithoutPartner() {
        String path = "./data/pair_without_partner.properties";
        Config config = new Config(path);
        assertThat(catchThrowable(config::load))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairContainsInvalidForm() {
        String path = "./data/pair_contains_invalid_form.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Aleksandr=1");
    }

    @Test
    void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        assertThat(catchThrowable(config::load))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutEqualsSign() {
        String path = "./data/pair_without_equal_sign.properties";
        Config config = new Config(path);
        assertThat(catchThrowable(config::load))
                .isInstanceOf(IllegalArgumentException.class);
    }
}