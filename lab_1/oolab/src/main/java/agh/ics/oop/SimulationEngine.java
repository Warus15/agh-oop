package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moves;
    private final IWorldMap map;

    private final ArrayList<Animal> animals;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions) {
        this.moves = moves;
        this.map = map;

        animals = new ArrayList<>();

        for (Vector2d v : initialPositions) {
            Animal animal = new Animal(map, v);
            placeAnimal(animal);
        }
    }

    public void placeAnimal(Animal animal) {
        animals.add(animal);
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
                animals.get(i % m).move(moves[i]);
                System.out.println(map);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
