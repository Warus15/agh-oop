package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(MapDirection orientation, Vector2d position) {
        this.orientation = orientation;
        this.position = position;
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    void move(MoveDirection direction) {
        final Vector2d BORDER_LOWER_LEFT = new Vector2d(0, 0);
        final Vector2d BORDER_UPPER_RIGHT = new Vector2d(4, 4);

        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                Vector2d tmp = new Vector2d(position.x, position.y).add(orientation.toUnitVector());
                if (tmp.x >= 0 && tmp.x <= 4 && tmp.y >= 0 && tmp.y <= 4) {
                    position = tmp;
                }
            }
            case BACKWARD -> {
                Vector2d tmp = new Vector2d(position.x, position.y).subtract(orientation.toUnitVector());
                if (tmp.x >= 0 && tmp.x <= 4 && tmp.y >= 0 && tmp.y <= 4) {
                    position = tmp;
                }
            }
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "orientation=" + orientation +
                ", position=" + position.toString() +
                '}';
    }
}
