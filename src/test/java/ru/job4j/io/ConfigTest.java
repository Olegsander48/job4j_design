package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairWithoutComment() {
        String path = "data/pair_with_blank_spaces.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.version")).isEqualTo("final");
    }

    @Test
    void whenBreakingPattern() {
        String path = "data/breaking_pattern.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenSeveralEqualsSigns() {
        String path = "data/several_equals_signs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isBlank();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=9");
    }
}