package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] directions) throws IllegalArgumentException{
        MoveDirection[] result;

        result = Arrays.stream(directions).map(dir ->
                        switch (dir) {
                            case "f", "forward" -> MoveDirection.FORWARD;
                            case "b", "backward" -> MoveDirection.BACKWARD;
                            case "r", "right" -> MoveDirection.RIGHT;
                            case "l", "left" -> MoveDirection.LEFT;
                            default -> throw new IllegalArgumentException(dir + " is not legal move");
                        }
                ).toArray(MoveDirection[]::new);

        return result;
    }
}
