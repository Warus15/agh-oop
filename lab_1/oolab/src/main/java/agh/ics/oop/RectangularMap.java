package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width, int height) {
        super(width, height);
    }

    @Override
    protected void calculateBorders() {
        lowerVisualizationBorder = new Vector2d(0,0);
        upperVisualizationBorder = new Vector2d(WIDTH, HEIGHT);
    }

    @Override
    public void removeElementAt(Vector2d position) {

    }
}
