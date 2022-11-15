package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap{
    private final int WIDTH;
    private final int HEIGHT;

    private final ArrayList<Animal> animals;

    private final MapVisualizer mapVisualizer;

    public RectangularMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        animals = new ArrayList<>();
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d LOWER_BORDER = new Vector2d(0, 0);
        Vector2d UPPER_BORDER = new Vector2d(WIDTH, HEIGHT);

        return !isOccupied(position) && position.follows(LOWER_BORDER) && position.precedes(UPPER_BORDER);
    }

    @Override
    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition())){
            return false;
        }

        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPosition().equals(position)){
                return true;
            }
        }

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }

        return null;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(
                new Vector2d(0,0),
                new Vector2d(WIDTH, HEIGHT)
        );
    }
}
