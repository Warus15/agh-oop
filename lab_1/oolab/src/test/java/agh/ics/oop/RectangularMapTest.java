package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveTo() {
        RectangularMap map = new RectangularMap(10, 10);

        assertFalse(map.canMoveTo(new Vector2d(11, 0)));
        assertFalse(map.canMoveTo(new Vector2d(-11, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, 11)));
        assertFalse(map.canMoveTo(new Vector2d(0, -11)));

        Vector2d animalPosition = new Vector2d(5, 5);
        Animal animal = new Animal(MapDirection.NORTH, animalPosition);
        map.place(animal);

        assertFalse(map.canMoveTo(animalPosition));
    }

    @Test
    void place() {
        Vector2d position = new Vector2d(5,5);
        RectangularMap map = new RectangularMap(10, 10);
        Animal animal = new Animal(MapDirection.NORTH, position);

        assertTrue(map.place(animal));
        assertTrue(map.mapElements.containsValue(animal));

        Animal animal2 = new Animal(MapDirection.EAST, position);

        assertFalse(map.place(animal2));
        assertFalse(map.mapElements.containsValue(animal2));

    }

    @Test
    void isOccupied() {
        Vector2d position = new Vector2d(5,5);
        Vector2d nextPosition = new Vector2d(5, 6);
        RectangularMap map = new RectangularMap(10, 10);
        Animal animal = new Animal(map, position);

        assertFalse(map.isOccupied(position));
        map.place(animal);
        assertTrue(map.isOccupied(position));

        assertFalse(map.isOccupied(nextPosition));
        animal.move(MoveDirection.FORWARD);
        assertTrue(map.isOccupied(nextPosition));
        assertFalse(map.isOccupied(position));
    }

    @Test
    void objectAt() {
        Vector2d position = new Vector2d(5,5);
        RectangularMap map = new RectangularMap(10, 10);
        Animal animal = new Animal(map, position);
        map.place(animal);

        assertNull(map.objectAt(new Vector2d(0,0)));
        assertEquals(map.objectAt(position), animal);
    }

    @Test
    void testToString() {
    }
}