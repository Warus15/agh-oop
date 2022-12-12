package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public String getTextureName() {
        return "grass.jpg";
    }

    @Override
    public String toString() {
        return "*";
    }
}
