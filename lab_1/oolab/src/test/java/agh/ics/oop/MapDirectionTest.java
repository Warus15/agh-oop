package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void testToString() {
    }

    @Test
    void next() {
        assertEquals(MapDirection.NORTH.next(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH);
        assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTH);
    }

    @Test
    void previous() {
        assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH);
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH);
    }

    @Test
    void toUnitVector() {
        assertEquals(MapDirection.NORTH.toUnitVector(), new Vector2d(0, 1));
        assertEquals(MapDirection.EAST.toUnitVector(), new Vector2d(1, 0));
        assertEquals(MapDirection.SOUTH.toUnitVector(), new Vector2d(0, -1));
        assertEquals(MapDirection.WEST.toUnitVector(), new Vector2d(-1, 0));
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}