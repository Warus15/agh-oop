package agh.ics.oop;

import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private final int GRASS_LIMIT;

    public GrassField(int amount) {
        super();

        GRASS_LIMIT = (int) Math.round(Math.sqrt(10 * amount));

        for (int i = 0; i < amount; ++i) {
            placeRandomGrass(null);
        }
    }

    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private boolean canPlaceGrass(Vector2d position) {
        return !super.isOccupied(position);
    }

    private Vector2d findGrassPosition(Vector2d toExclude) {
        final int occupiedFields = mapElements.size();
        final boolean hasFiniteBorders = WIDTH != Integer.MAX_VALUE && HEIGHT != Integer.MAX_VALUE;
        final int maxAmount = WIDTH * HEIGHT;

        if (hasFiniteBorders && (occupiedFields == maxAmount || (toExclude != null && occupiedFields == maxAmount - 1))) {
            return null;
        }

        int x, y;

        do {
            x = getRandomNumber(0, GRASS_LIMIT);
            y = getRandomNumber(0, GRASS_LIMIT);
        } while (!canPlaceGrass(new Vector2d(x, y)) || ( toExclude != null && new Vector2d(x, y).equals(toExclude)));

        return new Vector2d(x, y);
    }

    public boolean placeRandomGrass(Vector2d toExclude) {
        Vector2d grassPosition = findGrassPosition(toExclude);

        if (grassPosition == null) {
            return false;
        }

        this.mapElements.put(grassPosition, new Grass(grassPosition));

        return true;
    }

    /**
     * Function used in tests to place grass at given position
     * This one assumes that the place is not occupied
     * @param position
     *              Position at which grass should be placed
     */
    public void placeGrass(Vector2d position){
        this.mapElements.put(position, new Grass(position));

    }

    @Override
    protected void calculateBorders() {
        lowerVisualizationBorder = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        upperVisualizationBorder = new Vector2d(0,0);

        for(IMapElement mapElement : mapElements.values()){
            Vector2d currentPosition = mapElement.getPosition();

            lowerVisualizationBorder = currentPosition.lowerLeft(lowerVisualizationBorder);
            upperVisualizationBorder = currentPosition.upperRight(upperVisualizationBorder);
        }
    }

    @Override
    public boolean place(Animal animal) {
        boolean wasPlaced = super.place(animal);

        if(wasPlaced){
            return true;
        }

        Object o = objectAt(animal.getPosition());

        if(o instanceof Animal){
            return false;
        }

        mapElements.put(animal.getPosition(), animal);
        placeRandomGrass(animal.getPosition());

        return true;
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        boolean wasPositionChanged = super.positionChanged(oldPosition, newPosition);

        if(wasPositionChanged){
            return true;
        }

        Animal animal = (Animal) mapElements.get(oldPosition);

        mapElements.put(newPosition, animal);
        mapElements.remove(oldPosition);
        placeRandomGrass(newPosition);

        return true;
    }
}
