package agh.ics.oop;

import javax.swing.*;
import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final Vector2d[] initialPositions;

    private final ArrayList<Animal> animals;

    private JFrame frame;
    private JTable mapTable;

    private boolean visualize = false;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;

        animals = new ArrayList<>();

        for (Vector2d v : initialPositions) {
            Animal animal = new Animal(map, v);
            placeAnimal(animal);
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions, JFrame frame) {
        this.moves = moves;
        this.map = map;
        this.initialPositions = initialPositions;
        this.frame = frame;
        this.mapTable = (JTable) frame.getContentPane().getComponent(0);

        animals = new ArrayList<>();

        for (Vector2d v : initialPositions) {
            Animal animal = new Animal(map, v);
            placeAnimal(animal);
        }

        visualize = true;
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

        if (visualize) {
            drawMap();

            for (int i = 0; i < n; ++i) {
                try {
                    Thread.sleep(1000);
                    animals.get(i % m).move(moves[i]);
                    System.out.println(map);
                    clearMap();
                    drawMap();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            for (int i = 0; i < n; ++i) {
                animals.get(i % m).move(moves[i]);
                System.out.println(map);
            }
        }
    }

    void clearMap() {
        for (int i = 0; i < mapTable.getRowCount(); ++i) {
            for (int j = 0; j < mapTable.getColumnCount(); ++j) {
                mapTable.setValueAt("", i, j);
            }
        }
    }

    void drawMap() {
        for (Animal animal : animals) {
            int x = animal.getPosition().x;
            int y = mapTable.getRowCount() - animal.getPosition().y;
            mapTable.setValueAt(animal, y, x);
        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
