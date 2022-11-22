package agh.ics.oop;

import agh.ics.oop.IMapElement;
import agh.ics.oop.Vector2d;

public class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    @Override
    public Vector2d getPosition() {
        return position;
    }
}
