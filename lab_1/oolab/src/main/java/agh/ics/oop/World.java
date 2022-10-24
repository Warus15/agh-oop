package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        Direction[] directions = convertDirections(args);
        run(directions);
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

    public static Direction[] convertDirections(String[] dirs){
        Direction[] directions;

        directions = Arrays.stream(dirs).map(dir ->
            switch(dir){
                case "f"->
                    Direction.FORWARD;
                case "b" ->
                    Direction.BACKWARD;
                case "r" ->
                    Direction.RIGHT;
                case "l"->
                    Direction.LEFT;
                default ->
                    Direction.NONE;
        }).toArray(Direction[]::new);


        return directions;
    }

    public static void run(Direction[] vals){

        for(Direction arg : vals){
            switch (arg) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
                default -> {
                }
            }
        }
    }
}
