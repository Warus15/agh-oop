package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.util.Objects;

public class GUIElementBox {
    private final Image image;
    private final ImageView imageView;
    private final int IMAGE_SIZE = 30;

    private final Label description;

    private final VBox vBox;

    public GUIElementBox(IMapElement mapElement) {
//        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(mapElement.getTextureName())));
        image = switch (mapElement.getTextureName()){
            case "up.png" -> ImageLoader.UP;
            case "down.png" -> ImageLoader.DOWN;
            case "left.png" -> ImageLoader.LEFT;
            case "right.png" -> ImageLoader.RIGHT;
            case "grass.jpg" -> ImageLoader.GRASS;
            default -> null;
        };

        imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);

        if(mapElement instanceof Animal){
            description = new Label("Z " + mapElement.getPosition().toString());
        } else {
            description = new Label("Trawa");
        }

        vBox = new VBox();
        vBox.getChildren().addAll(imageView, description);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getElement(){
        return vBox;
    }
}
