package com.example.carracinggame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObject {
    protected double x, y, width, height; // Posisjon og størrelse på objektet
    protected Image image; // Bilde for objektet

    public GameObject(double x, double y, double width, double height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public boolean collidesWith(GameObject other) {
        // Sjekker om dette objektet kolliderer med et annet objekt
        return this.x < other.x + other.width && this.x + this.width > other.x &&
                this.y < other.y + other.height && this.y + this.height > other.y;
    }

    public double getY() {
        return y; // Henter Y-posisjonen til objektet
    }

    public abstract void draw(GraphicsContext gc); // Abstrakt metode for å tegne objektet
}
