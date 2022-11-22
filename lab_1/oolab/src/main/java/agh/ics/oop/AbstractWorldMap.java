package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {

    protected ArrayList<Animal> animals = new ArrayList<>();
    protected final int WIDTH;
    protected final int HEIGHT;

    protected final Vector2d borderStart = new Vector2d(0, 0);
    protected final Vector2d borderEnd;

    protected Vector2d lowerVisualizationBorder;
    protected Vector2d upperVisualizationBorder;

    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public AbstractWorldMap() {
        WIDTH = Integer.MAX_VALUE;
        HEIGHT = Integer.MAX_VALUE;
        borderEnd = new Vector2d(WIDTH, HEIGHT);
    }

    public AbstractWorldMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        borderEnd = new Vector2d(WIDTH, HEIGHT);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean occupiedByAnimal = objectAt(position) instanceof Animal;
        boolean inBounds = position.follows(borderStart) && position.precedes(borderEnd);

        return inBounds && !occupiedByAnimal;
    }


    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition())) {
            return false;
        }

        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }

        return null;
    }

    abstract protected void calculateBorders();

    public ArrayList<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void setAnimals(ArrayList<Animal> animals){
        this.animals = new ArrayList<>(animals);
    }

    @Override
    public String toString() {
        calculateBorders();
        return mapVisualizer.draw(lowerVisualizationBorder, upperVisualizationBorder);
    }
}
