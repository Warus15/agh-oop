package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean precedes(Vector2d other){
        return x <= other.x && y <= other.y;
    }

    boolean follows(Vector2d other){
        return x >= other.x && y >= other.y;
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    Vector2d subtract(Vector2d other){
        return new Vector2d(x - other.x, y - other.y);
    }

    Vector2d upperRight(Vector2d other){
        return new Vector2d(
                Math.max(x, other.x),
                Math.max(y, other.y)
        );
    }

    Vector2d lowerLeft(Vector2d other){
        return new Vector2d(
                Math.min(x, other.x),
                Math.min(y, other.y)
        );
    }

    Vector2d opposite(){
        return new Vector2d(-x, -y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if(obj.getClass() != getClass()){
            return false;
        }

        return ((Vector2d) obj).x == x && ((Vector2d) obj).y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
