package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    private GrassField map;
    private ArrayList<Animal> animals;
    private ArrayList<Grass> grass;

    private final Vector2d[] animalsPositions = {
            new Vector2d(2, 2),
            new Vector2d(0, 0),
            new Vector2d(3, 4),
            new Vector2d(5, 5)
    };
    private final Vector2d[] grassPositions = {
            new Vector2d(2, 3),
            new Vector2d(4, 4),
            new Vector2d(1, 2),
    };


    @BeforeEach
    void beforeEach() {
        map = new GrassField(10);

        for (Vector2d v : animalsPositions) {
            Animal animal = new Animal(map, v);
            map.mapElements.put(v, animal);
        }

        for (Vector2d v : grassPositions) {
            Grass g = new Grass(v);
            map.mapElements.put(v, g);
        }
    }

    @Test
    void objectAt() {
        assertTrue(map.objectAt(new Vector2d(2, 2)) instanceof Animal);
        assertTrue(map.objectAt(new Vector2d(2, 3)) instanceof Grass);
        assertTrue(map.objectAt(new Vector2d(5, 5)) instanceof Animal);
        assertFalse(map.objectAt(new Vector2d(5, 5)) instanceof Grass);
        assertNull(map.objectAt(new Vector2d(10, 10)));
    }

    @Test
    void canMoveTo() {
        assertFalse(map.canMoveTo(new Vector2d(2, 2))); // Field containing animal
        assertTrue(map.canMoveTo(new Vector2d(3, 3))); // Empty field
        assertTrue(map.canMoveTo(new Vector2d(2, 3))); // Field containing grass
    }

    @Test
    void place() {
        assertFalse(map.place(new Animal(MapDirection.NORTH, new Vector2d(2, 2)))); // Field already contains animal
        assertTrue(map.place(new Animal(MapDirection.NORTH, new Vector2d(10, 10)))); // Empty field
        assertTrue(map.place(new Animal(MapDirection.NORTH, new Vector2d(4, 4)))); // Field with grass
    }

    @Test
    void isOccupied() {
        assertTrue(map.isOccupied(new Vector2d(2, 2))); // Field containing animal
        assertFalse(map.isOccupied(new Vector2d(3, 3))); // Empty field
        assertTrue(map.isOccupied(new Vector2d(2, 3))); // Field containing grass
    }
}