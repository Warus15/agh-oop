package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {
    private MapDirection orientation;
    private IWorldMap map;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(MapDirection orientation, Vector2d position) {
        this.orientation = orientation;
        this.position = position;
    }

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d tmp = new Vector2d(position.x, position.y).add(orientation.toUnitVector());
                if (map.canMoveTo(tmp)) {
                    map.removeElementAt(tmp);
                    position = tmp;
                }
            }
            case BACKWARD -> {
                Vector2d tmp = new Vector2d(position.x, position.y).subtract(orientation.toUnitVector());
                if (map.canMoveTo(tmp)) {
                    map.removeElementAt(tmp);
                    position = tmp;
                }
            }
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return switch(orientation){
            case NORTH -> "N";
            case EAST -> "E";
            case WEST -> "W";
            case SOUTH -> "S";
        };
    }
}
