package agh.ics.oop;

import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected HashMap<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected final int WIDTH;
    protected final int HEIGHT;

    protected final Vector2d borderStart = new Vector2d(0, 0);
    protected final Vector2d borderEnd;

    protected final MapBoundary mapBoundary;

    protected Vector2d lowerVisualizationBorder;
    protected Vector2d upperVisualizationBorder;

    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public AbstractWorldMap() {
        WIDTH = Integer.MAX_VALUE;
        HEIGHT = Integer.MAX_VALUE;
        borderEnd = new Vector2d(WIDTH, HEIGHT);

        mapBoundary = new MapBoundary();
    }

    public AbstractWorldMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        borderEnd = new Vector2d(WIDTH, HEIGHT);

        mapBoundary = new MapBoundary();
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        boolean newPositionContainsGrass = isOccupied(newPosition);

        if(newPositionContainsGrass){
            return false;
        }

        Animal animal = (Animal) mapElements.get(oldPosition);

        mapElements.remove(oldPosition);
        mapElements.put(newPosition, animal);

        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean occupiedByAnimal = objectAt(position) instanceof Animal;
        boolean inBounds = position.follows(borderStart) && position.precedes(borderEnd);

        return inBounds && !occupiedByAnimal;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        if (isOccupied(animal.getPosition())) {
            throw new IllegalArgumentException(animal.getPosition() + " is already occupied");
        }

        mapElements.put(animal.getPosition(), animal);
        mapBoundary.addVector(animal.getPosition());

        animal.addObserver(this);
        animal.addObserver(mapBoundary);

        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    abstract protected void calculateBorders();

    @Override
    public String toString() {
        calculateBorders();
        return mapVisualizer.draw(lowerVisualizationBorder, upperVisualizationBorder);
    }

    public Vector2d getLowerVisualizationBorder() {
        return lowerVisualizationBorder;
    }

    public Vector2d getUpperVisualizationBorder() {
        return upperVisualizationBorder;
    }
}
