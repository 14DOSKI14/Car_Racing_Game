package com.example.carracinggame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Obstacle extends GameObject {
    public Obstacle(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image); // Kaller superklassens konstruktør
    }

    public void moveDown(double speed) {
        this.y += speed; // Flytter hindringen nedover
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y, width, height); // Tegner hindringen på skjermen
    }
}
