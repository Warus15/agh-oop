package agh.ics.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.PaintEvent;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    private final int MAP_WIDTH = 10;
    private final int MAP_HEIGHT = 10;


    @Test
    void collisionTest(){
        String[] args = {"f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(MAP_WIDTH, MAP_HEIGHT);

        Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(0,0),
                new Vector2d(0,1),
                new Vector2d(5, 5),
        };

        SimulationEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS);
        engine.run();

//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(0, 0));
//        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(0, 2));
//        assertEquals(engine.getAnimals().get(2).getPosition(), new Vector2d(5, 6));
    }

    @Test
    void standardTest(){
        String[] args = {"f", "r", "f", "l", "f", "r", "r", "b"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(MAP_WIDTH, MAP_HEIGHT);

        Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(0,0),
        };

        SimulationEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS);
        engine.run();

//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(1, 3));
    }

    @Test
    void orderTest(){
        String[] args = {"f", "b", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(MAP_WIDTH, MAP_HEIGHT);

        Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(1, 1),
                new Vector2d(2, 1),
                new Vector2d(3, 1),
        };

        SimulationEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS);
        engine.run();

//        assertEquals(engine.getAnimals().get(0).getPosition(), new Vector2d(1, 3));
//        assertEquals(engine.getAnimals().get(1).getPosition(), new Vector2d(2, 0));
//        assertEquals(engine.getAnimals().get(2).getPosition(), new Vector2d(3, 2));
    }

    @Test
    void run() {
    }
}