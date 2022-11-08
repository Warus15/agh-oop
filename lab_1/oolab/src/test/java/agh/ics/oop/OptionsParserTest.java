package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser op = new OptionsParser();

        String[] t1 = {"f", "b", "r", "l"};
        MoveDirection[] a1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assert Objects.deepEquals(op.parse(t1), a1);

        String[] t2 = {"f", " ", "f"};
        MoveDirection[] a2 = {MoveDirection.FORWARD, MoveDirection.FORWARD};
        assert Objects.deepEquals(op.parse(t2), a2);

        String[] t3 = {"b", "asd", "r"};
        MoveDirection[] a3 = {MoveDirection.BACKWARD, MoveDirection.RIGHT};
        assert Objects.deepEquals(op.parse(t3), a3);

        String[] t4 = {"forward", "backward", "right", "left"};
        MoveDirection[] a4 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assert Objects.deepEquals(op.parse(t4), a4);
    }
}