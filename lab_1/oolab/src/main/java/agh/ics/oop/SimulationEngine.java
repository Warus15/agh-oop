package agh.ics.oop;

import javafx.application.Platform;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SimulationEngine implements Runnable {

    private MoveDirection[] moves;
    private final IWorldMap map;
    private final HashMap<Vector2d, Animal> animals = new HashMap<>();

    private final Vector2d[] positions;

    private final IPositionChangeObserver GUIObserver;

    private final int MOVE_DELAY;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions) {
        this.moves = moves;
        this.map = map;
        this.positions = initialPositions;
        GUIObserver = null;
        MOVE_DELAY = 300;

        for (Vector2d v : initialPositions) {
            Animal animal = new Animal(map, v);
            placeAnimal(animal);
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] initialPositions, IPositionChangeObserver observer, int delay) {
        this.moves = moves;
        this.map = map;
        this.positions = initialPositions;
        GUIObserver = observer;
        MOVE_DELAY = delay;

        for (Vector2d v : initialPositions) {
            Animal animal = new Animal(map, v);
            placeAnimal(animal);
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] initialPositions, IPositionChangeObserver observer, int delay) {
        this.map = map;
        this.positions = initialPositions;
        GUIObserver = observer;
        MOVE_DELAY = delay;

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
        Platform.runLater(() -> {
            GUIObserver.positionChanged(null, null);
        });

        try {
            Thread.sleep(MOVE_DELAY);
        } catch (InterruptedException ex) {
            System.out.println("Ex: " + ex);
        }

        int n = moves.length;
        int m = animals.size();

        for (int i = 0; i < n; ++i) {
            try {
                animals.get(positions[i % m]).move(moves[i]);
                Platform.runLater(() -> {
                    GUIObserver.positionChanged(null, null);
                });
                System.out.println(map); //TODO: ASK WHY
                Thread.sleep(MOVE_DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Platform.runLater(() -> {
            GUIObserver.positionChanged(null, null);
        });

    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }
}
