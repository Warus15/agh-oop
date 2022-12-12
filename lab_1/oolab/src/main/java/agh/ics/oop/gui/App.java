package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver {

    private final int CELL_SIZE = 50;
    private final int SCALE_FACTOR = 1;
    private GrassField map;

    private Stage stage;
    private Scene scene;
    private final GridPane grid = new GridPane();

    private Thread engineThread;

    private final int MOVE_DELAY = 300;

    @Override
    public void init() throws Exception {
        try {
            super.init();

            final Vector2d[] INITIAL_POSITIONS = {
                    new Vector2d(2, 2), new Vector2d(3, 4)
            };

            map = new GrassField(5);

            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));

            SimulationEngine engine = new SimulationEngine(directions, map, INITIAL_POSITIONS, this, MOVE_DELAY);

            engineThread = new Thread(engine);

        } catch (IllegalArgumentException ex) {
            System.out.println("Exit with exception: " + ex);
            Platform.exit();
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        engineThread.start();
        stage.setScene(getScene());
        stage.show();
    }

    public Scene getScene() {
        grid.getChildren().clear();
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

                if (o == null) {
                    continue;
                }

                VBox element = new GUIElementBox((IMapElement) o).getElement();

                GridPane.setHalignment(element, HPos.CENTER);
                grid.add(element, j, i);
            }
        }

        return new Scene(grid,
                (width + 1) * CELL_SIZE * SCALE_FACTOR,
                (height + 1) * CELL_SIZE * SCALE_FACTOR);
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(()->{
            stage.setScene(null);
            stage.setScene(getScene());
            stage.show();
        });

        return true;
    }
}
