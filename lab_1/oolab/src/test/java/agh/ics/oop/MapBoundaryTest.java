package agh.ics.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapBoundaryTest {
    final Vector2d[] T1 = {
            new Vector2d(0, 0), new Vector2d(10, 10)
    };

    final Vector2d[] T2 = {
            new Vector2d(2, 10), new Vector2d(5, 7)
    };

    final Vector2d[] T3 = {
            new Vector2d(3, 4), new Vector2d(1, 10), new Vector2d(9, 7)
    };

    @Test
    void lowerLeft() {
        MapBoundary mb1 = new MapBoundary();
        MapBoundary mb2 = new MapBoundary();
        MapBoundary mb3 = new MapBoundary();

        for (Vector2d v : T1) {
            mb1.addVector(v);
        }

        for (Vector2d v : T2) {
            mb2.addVector(v);
        }

        for (Vector2d v : T3) {
            mb3.addVector(v);
        }

        assertEquals(mb1.lowerLeft(), new Vector2d(0, 0));
        assertEquals(mb2.lowerLeft(), new Vector2d(2, 7));
        assertEquals(mb3.lowerLeft(), new Vector2d(1, 4));
    }

    @Test
    void upperRight() {
        MapBoundary mb1 = new MapBoundary();
        MapBoundary mb2 = new MapBoundary();
        MapBoundary mb3 = new MapBoundary();

        for (Vector2d v : T1) {
            mb1.addVector(v);
        }

        for (Vector2d v : T2) {
            mb2.addVector(v);
        }

        for (Vector2d v : T3) {
            mb3.addVector(v);
        }

        assertEquals(mb1.upperRight(), new Vector2d(10, 10));
        assertEquals(mb2.upperRight(), new Vector2d(5, 10));
        assertEquals(mb3.upperRight(), new Vector2d(9, 10));
    }
}