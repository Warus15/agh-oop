package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        Vector2d v = new Vector2d(1, 1);

        Vector2d other = new Vector2d(0, 0);
        assertFalse(v.precedes(other));

        other = new Vector2d(0, 1);
        assertFalse(v.precedes(other));

        other = new Vector2d(1, 0);
        assertFalse(v.precedes(other));

        other = new Vector2d(2, 2);
        assertTrue(v.precedes(other));

        other = new Vector2d(2, 1);
        assertTrue(v.precedes(other));

        other = new Vector2d(1, 2);
        assertTrue(v.precedes(other));

        other = new Vector2d(1, 1);
        assertTrue(v.precedes(other));
    }

    @Test
    void follows() {
        Vector2d v = new Vector2d(1, 1);

        Vector2d other = new Vector2d(0, 0);
        assertTrue(v.follows(other));

        other = new Vector2d(0, 1);
        assertTrue(v.follows(other));

        other = new Vector2d(1, 0);
        assertTrue(v.follows(other));

        other = new Vector2d(1, 1);
        assertTrue(v.follows(other));

        other = new Vector2d(2, 2);
        assertFalse(v.follows(other));

        other = new Vector2d(2, 1);
        assertFalse(v.follows(other));

        other = new Vector2d(1, 2);
        assertFalse(v.follows(other));
    }

    @Test
    void add() {
        Vector2d v = new Vector2d(0, 0);

        Vector2d other = new Vector2d(1, 0);
        assertEquals(v.add(other), new Vector2d(1, 0));

        other = new Vector2d(0, 1);
        assertEquals(v.add(other), new Vector2d(0, 1));

        other = new Vector2d(1, 1);
        assertEquals(v.add(other), new Vector2d(1, 1));

        other = new Vector2d(-1, 0);
        assertEquals(v.add(other), new Vector2d(-1, 0));

        other = new Vector2d(0, -1);
        assertEquals(v.add(other), new Vector2d(0, -1));

        other = new Vector2d(-1, -1);
        assertEquals(v.add(other), new Vector2d(-1, -1));
    }

    @Test
    void subtract() {
        Vector2d v = new Vector2d(0, 0);

        Vector2d other = new Vector2d(1, 0);
        assertEquals(v.subtract(other), new Vector2d(-1, 0));

        other = new Vector2d(0, 1);
        assertEquals(v.subtract(other), new Vector2d(0, -1));

        other = new Vector2d(1, 1);
        assertEquals(v.subtract(other), new Vector2d(-1, -1));

        other = new Vector2d(-1, 0);
        assertEquals(v.subtract(other), new Vector2d(1, 0));

        other = new Vector2d(0, -1);
        assertEquals(v.subtract(other), new Vector2d(0, 1));

        other = new Vector2d(-1, -1);
        assertEquals(v.subtract(other), new Vector2d(1, 1));
    }

    @Test
    void upperRight() {
        Vector2d v = new Vector2d(0, 0);

        Vector2d other = new Vector2d(0, 0);
        assertEquals(v.upperRight(other), new Vector2d(0, 0));

        other = new Vector2d(1, 0);
        assertEquals(v.upperRight(other), new Vector2d(1, 0));

        other = new Vector2d(0, 1);
        assertEquals(v.upperRight(other), new Vector2d(0, 1));

        other = new Vector2d(1, 1);
        assertEquals(v.upperRight(other), new Vector2d(1, 1));

        other = new Vector2d(-1, 0);
        assertEquals(v.upperRight(other), new Vector2d(0, 0));

        other = new Vector2d(0, -1);
        assertEquals(v.upperRight(other), new Vector2d(0, 0));

        other = new Vector2d(-1, -1);
        assertEquals(v.upperRight(other), new Vector2d(0, 0));

        other = new Vector2d(-2, 2);
        assertEquals(v.upperRight(other), new Vector2d(0, 2));

        other = new Vector2d(2, -2);
        assertEquals(v.upperRight(other), new Vector2d(2, 0));

        other = new Vector2d(3, 4);
        assertEquals(v.upperRight(other), new Vector2d(3, 4));
    }

    @Test
    void lowerLeft() {
        Vector2d v = new Vector2d(0, 0);

        Vector2d other = new Vector2d(0, 0);
        assertEquals(v.lowerLeft(other), new Vector2d(0, 0));

        other = new Vector2d(1, 0);
        assertEquals(v.lowerLeft(other), new Vector2d(0, 0));

        other = new Vector2d(0, 1);
        assertEquals(v.lowerLeft(other), new Vector2d(0, 0));

        other = new Vector2d(1, 1);
        assertEquals(v.lowerLeft(other), new Vector2d(0, 0));

        other = new Vector2d(-1, 0);
        assertEquals(v.lowerLeft(other), new Vector2d(-1, 0));

        other = new Vector2d(0, -1);
        assertEquals(v.lowerLeft(other), new Vector2d(0, -1));

        other = new Vector2d(-1, -1);
        assertEquals(v.lowerLeft(other), new Vector2d(-1, -1));

        other = new Vector2d(-2, 2);
        assertEquals(v.lowerLeft(other), new Vector2d(-2, 0));

        other = new Vector2d(2, -2);
        assertEquals(v.lowerLeft(other), new Vector2d(0, -2));

        other = new Vector2d(3, 4);
        assertEquals(v.lowerLeft(other), new Vector2d(0, 0));
    }

    @Test
    void opposite() {
        Vector2d v = new Vector2d(0, 0);
        assertEquals(v.opposite(), new Vector2d(0, 0));

        v = new Vector2d(1, 0);
        assertEquals(v.opposite(), new Vector2d(-1, 0));

        v = new Vector2d(0, 1);
        assertEquals(v.opposite(), new Vector2d(0, -1));

        v = new Vector2d(-1, -2);
        assertEquals(v.opposite(), new Vector2d(1, 2));

        v = new Vector2d(2, 1);
        assertEquals(v.opposite(), new Vector2d(-2, -1));

        v = new Vector2d(-1, 2);
        assertEquals(v.opposite(), new Vector2d(1, -2));

        v = new Vector2d(1, -2);
        assertEquals(v.opposite(), new Vector2d(-1, 2));
    }

    @Test
    void testEquals() {
        Vector2d v = new Vector2d(0,0);

        Vector2d other = new Vector2d(0,0);
        assertTrue(v.equals(other));

        other = new Vector2d(1, 0);
        assertFalse(v.equals(other));

        other = new Vector2d(0, 1);
        assertFalse(v.equals(other));

        other = new Vector2d(1, 1);
        assertFalse(v.equals(other));

        v = new Vector2d(-1, -2);

        other = new Vector2d(-1, -2);
        assertTrue(v.equals(other));

        other = new Vector2d(-1, 2);
        assertFalse(v.equals(other));

        other = new Vector2d(1, -2);
        assertFalse(v.equals(other));

        other = new Vector2d(1, 2);
        assertFalse(v.equals(other));
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
        Vector2d v = new Vector2d(0, 0);
        assertEquals(v.toString(), "(0,0)");

        v = new Vector2d(1, 0);
        assertEquals(v.toString(), "(1,0)");

        v = new Vector2d(0, 1);
        assertEquals(v.toString(), "(0,1)");

        v = new Vector2d(1, 1);
        assertEquals(v.toString(), "(1,1)");

        v = new Vector2d(-1, 0);
        assertEquals(v.toString(), "(-1,0)");

        v = new Vector2d(0, -1);
        assertEquals(v.toString(), "(0,-1)");

        v = new Vector2d(-1, -1);
        assertEquals(v.toString(), "(-1,-1)");
    }
}