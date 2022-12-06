package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    private final int CELL_SIZE = 35;
    private final int SCALE_FACTOR = 1;
    private GrassField map;

    @Override
    public void init() throws Exception {
        super.init();

        final Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(2, 2), new Vector2d(3, 4)
        };

//        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f"});
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "f", "r", "f", "f", "l", "f", "f"});
        map = new GrassField(5);

        IEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS);
        engine.run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Zwierzak");
        Scene scene = new Scene(label, 400, 400);

        primaryStage.setScene(getScene());
        primaryStage.show();
    }

    public Scene getScene() {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        //Calculating borders
        Vector2d lowerLeft = map.getLowerVisualizationBorder();
        Vector2d upperRight = map.getUpperVisualizationBorder();

        int width = upperRight.x - lowerLeft.x + 1;
        int height = upperRight.y - lowerLeft.y + 1;

        for (int i = 0; i <= width; ++i) {
            grid.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }

        for (int i = 0; i <= height; ++i) {
            grid.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }

        // Setting grid labels

        Label xyLabel = new Label("y/x");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        grid.add(xyLabel, 0, 0);

        for (int i = lowerLeft.x, j = 1; i <= upperRight.x; ++i, ++j) {
            Label label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, j, 0);
        }

        for (int i = upperRight.y, j = 1; i >= lowerLeft.y; --i, ++j) {
            Label label = new Label(Integer.toString(i));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, j);
        }

        // Getting map content

        for (int y = upperRight.y, i = 1; y >= lowerLeft.y; --y, ++i) {
            for (int x = lowerLeft.x, j = 1; x <= upperRight.x; ++x, ++j) {
                Object o = map.objectAt(new Vector2d(x, y));
                Label elementLabel = new Label(o != null ? o.toString() : " ");

                GridPane.setHalignment(elementLabel, HPos.CENTER);
                grid.add(elementLabel, j, i);
            }
        }

        return new Scene(grid,
                (width + 1) * CELL_SIZE * SCALE_FACTOR,
                (height + 1) * CELL_SIZE * SCALE_FACTOR);
    }
}
