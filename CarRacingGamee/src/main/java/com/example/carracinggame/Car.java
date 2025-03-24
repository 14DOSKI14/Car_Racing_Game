package com.example.carracinggame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Car extends GameObject {
    private static final double SPEED = 5; // Bevegelseshastighet
    private boolean movingLeft = false;  // Indikerer om bilen beveger seg til venstre
    private boolean movingRight = false; // Indikerer om bilen beveger seg til høyre

    public Car(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image); // Kaller superklassens konstruktør
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft; // Setter om bilen skal bevege seg til venstre
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight; // Setter om bilen skal bevege seg til høyre
    }

    public void update() {
        if (movingLeft && x > 100) {
            x -= SPEED; // Beveger bilen til venstre
        }
        if (movingRight && x < 505 - width) { // Sjekker at bilen ikke går utenfor banen
            x += SPEED; // Beveger bilen til høyre
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y, width, height); // Tegner bilen på skjermen
    }
}
