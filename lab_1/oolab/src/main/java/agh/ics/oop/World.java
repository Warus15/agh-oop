package agh.ics.oop;

import java.util.HashMap;

public class World {
    public static void main(String[] args) {
        final Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(2, 2), new Vector2d(3, 4)
        };

        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "f", "r", "f", "f", "l", "f", "f"});
        IWorldMap map = new GrassField(5);

        IEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS);
        engine.run();
    }
}
