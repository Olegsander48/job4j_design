package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .containsIgnoringCase("cube")
                .isEqualTo("Cube");
    }

    @Test
    void isCubeHas8Vertex() {
        Box box = new Box(8, 12);
        Integer count = box.getNumberOfVertices();
        assertThat(count).isPositive()
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void isTetrahedronHas4Vertex() {
        Box box = new Box(4, 6);
        Integer count = box.getNumberOfVertices();
        assertThat(count).isNotZero()
                .isGreaterThan(3)
                .isEqualTo(4);
    }

    @Test
    void isCubeExist() {
        Box box = new Box(7, 12);
        boolean exist = box.isExist();
        assertThat(exist).isNotNull()
                .isFalse();
    }

    @Test
    void isSphereExist() {
        Box box = new Box(0, 1);
        boolean exist = box.isExist();
        assertThat(exist).isNotNull()
                .isTrue();
    }

    @Test
    void isTetrahedronAreaEquals62Point3() {
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area).isGreaterThan(60d)
                .isLessThan(65d)
                .isEqualTo(62.3d, withPrecision(0.1d));
    }

    @Test
    void isCubeAreaEquals864() {
        Box box = new Box(8, 12);
        double area = box.getArea();
        assertThat(area).isFinite()
                .isNotZero()
                .isEqualTo(864d, withPrecision(0.01d));
    }
}