package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void orientationTest(){
        Animal animal = new Animal();
        assertEquals(animal.getOrientation(), MapDirection.NORTH);

        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.getOrientation(), MapDirection.EAST);

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        assertEquals(animal.getOrientation(), MapDirection.WEST);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        assertEquals(animal.getOrientation(), MapDirection.SOUTH);
    }

    @Test
    void positionTest(){
        Animal animal = new Animal();
        assertEquals(animal.getPosition(), new Vector2d(2, 2));

        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(1, 2));

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(animal.getPosition(), new Vector2d(2, 2));

    }

    @Test
    void borderTest(){
        Vector2d LOWER_LEFT_BORDER = new Vector2d(0,0);
        Animal animal1 = new Animal(MapDirection.SOUTH, LOWER_LEFT_BORDER);

        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1.getPosition(), LOWER_LEFT_BORDER);

        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        assertEquals(animal1.getPosition(), LOWER_LEFT_BORDER);

        Vector2d LOWER_RIGHT_BORDER = new Vector2d(4,0);
        Animal animal2 = new Animal(MapDirection.SOUTH, new Vector2d(4, 0));

        animal2.move(MoveDirection.FORWARD);
        assertEquals(animal2.getPosition(), LOWER_RIGHT_BORDER);

        animal2.move(MoveDirection.LEFT);
        animal2.move(MoveDirection.FORWARD);
        assertEquals(animal2.getPosition(), LOWER_RIGHT_BORDER);

        Vector2d UPPER_RIGHT_BORDER = new Vector2d(4,4);
        Animal animal3 = new Animal(MapDirection.NORTH, UPPER_RIGHT_BORDER);

        animal3.move(MoveDirection.FORWARD);
        assertEquals(animal3.getPosition(), UPPER_RIGHT_BORDER);

        animal3.move(MoveDirection.RIGHT);
        animal3.move(MoveDirection.FORWARD);
        assertEquals(animal3.getPosition(), UPPER_RIGHT_BORDER);

        Vector2d UPPER_LEFT_BORDER = new Vector2d(0,4);
        Animal animal4 = new Animal(MapDirection.NORTH, UPPER_LEFT_BORDER);

        animal4.move(MoveDirection.FORWARD);
        assertEquals(animal4.getPosition(), UPPER_LEFT_BORDER);

        animal4.move(MoveDirection.LEFT);
        animal4.move(MoveDirection.FORWARD);
        assertEquals(animal4.getPosition(), UPPER_LEFT_BORDER);
    }

    @Test
    void isAt() {
    }

    @Test
    void move() {
    }

    @Test
    void testToString() {
    }
}