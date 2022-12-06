package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private final TreeSet<Vector2d> xAxis = new TreeSet<>(
            (v1, v2) -> v1.x != v2.x ? v1.x - v2.x : v1.y - v2.y
    );
    private final TreeSet<Vector2d> yAxis = new TreeSet<>(
            (v1, v2) -> v1.y != v2.y ? v1.y - v2.y : v1.x - v2.x
    );

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeVector(oldPosition);
        addVector(newPosition);

        return true;
    }

    public void addVector(Vector2d v) {
        xAxis.add(v);
        yAxis.add(v);
    }

    public void removeVector(Vector2d v) {
        xAxis.remove(v);
        yAxis.remove(v);
    }

    public Vector2d lowerLeft() {
        return new Vector2d(xAxis.first().x, yAxis.first().y);
    }

    public Vector2d upperRight() {
        return new Vector2d(xAxis.last().x, yAxis.last().y);
    }
}
