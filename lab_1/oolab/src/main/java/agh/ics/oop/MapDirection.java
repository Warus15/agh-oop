package agh.ics.oop;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    @Override
    public String toString() {
        return switch(this){
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
        };
    }

    MapDirection next(){
        return MapDirection.values()[(this.ordinal() + 1) % 4];
    }

    MapDirection previous(){
        return MapDirection.values()[(this.ordinal() + 3) % 4];
    }

    Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
            case EAST -> new Vector2d(1, 0);
        };
    }
}
