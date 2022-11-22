package agh.ics.oop;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {
    private final int amount;
    private ArrayList<Grass> grass = new ArrayList<>();

    private final int GRASS_LIMIT;

    public GrassField(int amount) {
        super();

        this.amount = amount;
        GRASS_LIMIT = (int) Math.round(Math.sqrt(10 * amount));

        for (int i = 0; i < amount; ++i) {
            placeRandomGrass(null);
        }
    }

    private int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private Grass getGrassAtPosition(Vector2d position) {
        for (Grass g : grass) {
            if (g.getPosition().equals(position)) {
                return g;
            }
        }

        return null;
    }

    private boolean canPlaceGrass(Vector2d position) {
        return !super.isOccupied(position);
    }

    private Vector2d findGrassPosition(Vector2d toExclude) {
        final int occupiedFields = animals.size() + grass.size();
        boolean hasFiniteBorders = WIDTH != Integer.MAX_VALUE && HEIGHT != Integer.MAX_VALUE;
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

        grass.add(new Grass(grassPosition));

        return true;
    }

    /**
     * Function used in tests to place grass at given position
     * This one assumes that the place is not occupied
     * @param position
     *              Position at which grass should be placed
     */
    public void placeGrass(Vector2d position){
        grass.add(new Grass(position));
    }

    @Override
    protected void calculateBorders() {
        if(animals.size() > 0){
            lowerVisualizationBorder = animals.get(0).getPosition();
            upperVisualizationBorder = animals.get(0).getPosition();
        } else if (grass.size() > 0){
            lowerVisualizationBorder = grass.get(0).getPosition();
            upperVisualizationBorder = grass.get(0).getPosition();
        } else {
            lowerVisualizationBorder = new Vector2d(0,0);
            upperVisualizationBorder = new Vector2d(0,0);
        }

        for (Animal animal : animals) {
            Vector2d currentPosition = animal.getPosition();

            lowerVisualizationBorder = currentPosition.lowerLeft(lowerVisualizationBorder);
            upperVisualizationBorder = currentPosition.upperRight(upperVisualizationBorder);
        }

        for (Grass g : grass) {
            Vector2d currentPosition = g.getPosition();

            lowerVisualizationBorder = currentPosition.lowerLeft(lowerVisualizationBorder);
            upperVisualizationBorder = currentPosition.upperRight(upperVisualizationBorder);
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Animal animal = (Animal) super.objectAt(position);

        if (animal != null) {
            return animal;
        }


        return getGrassAtPosition(position);
    }

    @Override
    public void removeElementAt(Vector2d position) {
        Object object = objectAt(position);

        if (object instanceof Grass) {
            grass.remove(object);
            placeRandomGrass(position);
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

        animals.add(animal);
        grass.remove(o);
        placeRandomGrass(animal.getPosition());

        return true;
    }

    public ArrayList<Grass> getGrass() {
        return new ArrayList<>(grass);
    }

    public void setGrass(ArrayList<Grass> grass){
        this.grass = new ArrayList<>(grass);
    }
}
