package agh.ics.oop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;

public class World {
    public static void main(String[] args) {

        final int MAP_WIDTH = 10;
        final int MAP_HEIGHT = 5;
        final int CELL_SIZE = 30;

        final Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(2,3),
        };

        JFrame frame = getFrame(MAP_WIDTH, MAP_HEIGHT, CELL_SIZE);
        frame.setVisible(true);

        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "f", "r", "f", "f", "b"});
        IWorldMap map = new RectangularMap(MAP_WIDTH, MAP_HEIGHT);

        IEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS, frame);
        engine.run();
    }

    public static JFrame getFrame(int width, int height, int cellSize){
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel(height, width);
        JTable table = new JTable(model);
        table.setRowHeight(cellSize);
        for (int i = 0; i < table.getColumnCount(); ++i){
            table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
            table.getColumnModel().getColumn(i).setMaxWidth(cellSize);
            table.getColumnModel().getColumn(i).setMinWidth(cellSize);
        }
        frame.getContentPane().add(table);

        return frame;
    }

    public static Direction[] convertDirections(String[] dirs) {
        Direction[] directions;

        directions = Arrays.stream(dirs).map(dir ->
                switch (dir) {
                    case "f" -> Direction.FORWARD;
                    case "b" -> Direction.BACKWARD;
                    case "r" -> Direction.RIGHT;
                    case "l" -> Direction.LEFT;
                    default -> Direction.NONE;
                }).toArray(Direction[]::new);


        return directions;
    }

    public static void run(Direction[] vals) {

        for (Direction arg : vals) {
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
