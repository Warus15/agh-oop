package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final HashMap<Vector2d, Animal> animals = new HashMap<>();

    private final Vector2d[] positions;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions) {
        this.moves = moves;
        this.map = map;
        this.positions = initialPositions;

        for (Vector2d v : initialPositions) {
            Animal animal = new Animal(map, v);
            placeAnimal(animal);
        }
    }

    public void placeAnimal(Animal animal) {
        animals.put(animal.getPosition(), animal);
        map.place(animal);
    }

    @Override
    public void run() {
        System.out.println(map);
        int n = moves.length;
        int m = animals.size();

        for (int i = 0; i < n; ++i) {
            try {
                Thread.sleep(3000);
                animals.get(positions[i % m]).move(moves[i]);

                //Todo: Change z-index with animal and grass
                System.out.println(map);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
