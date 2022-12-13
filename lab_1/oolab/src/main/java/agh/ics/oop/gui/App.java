package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver {

    private final int CELL_SIZE = 50;
    private final int SCALE_FACTOR = 1;
    private GrassField map;

    private Stage stage;
    private Scene scene;
    private final GridPane grid = new GridPane();

    private Thread engineThread;
    private SimulationEngine engine;

    private final int MOVE_DELAY = 1000;

    @Override
    public void init() throws Exception {
        try {
            super.init();

            final Vector2d[] INITIAL_POSITIONS = {
                    new Vector2d(2, 2), new Vector2d(3, 4)
            };

            map = new GrassField(5);

            engine = new SimulationEngine( map, INITIAL_POSITIONS, this, MOVE_DELAY);

            setGrid();

            engineThread = new Thread(engine);

        } catch (IllegalArgumentException ex) {
            System.out.println("Exit with exception: " + ex);
            Platform.exit();
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Vector2d[] INITIAL_POSITIONS = {
                new Vector2d(2, 2), new Vector2d(3, 4)
        };

        TextField directionsTextField = new TextField();

        Button startButton = new Button("Start");
        startButton.setOnAction((e) -> {
            try{
                MoveDirection[] directions = new OptionsParser().parse(directionsTextField.getText().split(" "));
                engine.setMoves(directions);
                engineThread.start();
            } catch(IllegalArgumentException ex){
                System.out.println("ex: " + ex);
                Platform.exit();
                System.exit(0);
            }
        });

        HBox controls = new HBox();
        controls.getChildren().addAll(startButton, directionsTextField);

        VBox container = new VBox();

        container.getChildren().addAll(grid, controls);

        stage = primaryStage;
        scene = new Scene(container, 1024, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void setGrid() {
        grid.setGridLinesVisible(false);
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();


        grid.setGridLinesVisible(true);

        //Calculating borders
        Vector2d lowerLeft = map.getLowerVisualizationBorder();
        Vector2d upperRight = map.getUpperVisualizationBorder();

        System.out.println(lowerLeft);
        System.out.println(upperRight);

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
    }

    @Override
    public boolean positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        long start = System.nanoTime();
        setGrid();
        System.out.println(System.nanoTime() - start);

        return true;
    }
}
