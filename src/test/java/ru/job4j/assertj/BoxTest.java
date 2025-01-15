package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Cube");
    }

    @Test
    void isNumberOfVerticesIs4() {
        Box box = new Box(4, 10);
        int countOfVertices = box.getNumberOfVertices();
        assertThat(countOfVertices).isNotZero()
                .isPositive()
                .isEven()
                .isEqualTo(4);
    }

    @Test
    void isNumberOfVerticesIs8() {
        Box box = new Box(8, 10);
        int countOfVertices = box.getNumberOfVertices();
        assertThat(countOfVertices).isNotZero()
                .isPositive()
                .isEven().isGreaterThan(4)
                .isLessThan(10)
                .isEqualTo(8);
    }

    @Test
    void figureExist() {
        Box box = new Box(8, 10);
        boolean figureExist = box.isExist();
        assertThat(figureExist).isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void figureDontExist() {
        Box box = new Box(-1, 10);
        boolean figureExist = box.isExist();
        assertThat(figureExist).isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void isAreaOfSphereIs1257Point63() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(1256.63d, withPrecision(0.01d))
                .isCloseTo(1256.63d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1256.63d)
                .isLessThan(1257.64d);
    }

    @Test
    void isAreaOfCubeIs600() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(600d, withPrecision(0.1d))
                .isCloseTo(600d, Percentage.withPercentage(1.0d))
                .isEqualTo(600d);
    }
}