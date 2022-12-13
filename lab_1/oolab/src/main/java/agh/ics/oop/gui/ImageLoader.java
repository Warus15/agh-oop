package agh.ics.oop.gui;

import javafx.scene.image.Image;

import java.util.Objects;

public class ImageLoader {
    public static Image UP = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("up_a.png")));
    public static Image DOWN = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("down_a.png")));
    public static Image LEFT = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("left_a.png")));
    public static Image RIGHT = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("right_a.png")));
    public static Image GRASS = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("grass.jpg")));
}
