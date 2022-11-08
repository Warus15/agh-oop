package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    MoveDirection[] parse(String[] directions) {
        MoveDirection[] result = new MoveDirection[directions.length];

        result = Arrays.stream(directions).map(dir ->
                        switch (dir) {
                            case "f", "forward" -> MoveDirection.FORWARD;
                            case "b", "backward" -> MoveDirection.BACKWARD;
                            case "r", "right" -> MoveDirection.RIGHT;
                            case "l", "left" -> MoveDirection.LEFT;
                            default -> null;
                        }
                ).filter(Objects::nonNull)
                .toArray(MoveDirection[]::new);

        return result;
    }
}
