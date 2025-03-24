package com.example.carracinggame;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;
    private static final int OBSTACLE_SPEED = 5;

    private Car playerCar;
    private ArrayList<Obstacle> obstacles;
    private long startTime;
    private boolean running;
    private Leaderboard leaderboard;
    private Image playerCarImage;
    private Image highWayImage;
    private Image obstacleImage;
    private Button restartButton;

    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Laster bilder for spillerbil og hindringer
        playerCarImage = new Image(getClass().getResourceAsStream("/images/playercar.png"));
        obstacleImage = new Image(getClass().getResourceAsStream("/images/obstaclecar.png"));
        highWayImage = new Image(getClass().getResourceAsStream("/images/highway.png"));

        leaderboard = new Leaderboard("leaderboard.txt"); // Initialiserer poengtavlen
        playerCar = new Car(WIDTH / 2 - 25, HEIGHT - 100, 35, 63, playerCarImage);
        obstacles = new ArrayList<>();

        // Setter opp restart-knappen
        restartButton = new Button("RESTART");
        restartButton.setOnAction(e -> restartGame(stage));
        restartButton.setVisible(false);
        root.getChildren().add(restartButton);

        // Håndterer tastetrykk for å styre bilen
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) playerCar.setMovingLeft(true);
            if (e.getCode() == KeyCode.RIGHT) playerCar.setMovingRight(true);
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) playerCar.setMovingLeft(false);
            if (e.getCode() == KeyCode.RIGHT) playerCar.setMovingRight(false);
        });

        stage.setScene(scene);
        stage.setTitle("Car Racing Game");
        stage.show();

        startGameLoop(gc); // Starter spill-løkken
    }

    private void startGameLoop(GraphicsContext gc) {
        running = true;
        startTime = System.currentTimeMillis();
        restartButton.setVisible(false);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!running) {
                    stop();
                    return;
                }
                update(); // Oppdaterer spilltilstanden
                drawBackground(gc); // Tegner bakgrunnen og spillobjektene
            }
        }.start();
    }

    private void update() {
        // Genererer hindringer tilfeldig
        if (Math.random() < 0.02) {
            int obstacleX = 100 + new Random().nextInt(370);
            obstacles.add(new Obstacle(obstacleX, -50, 33, 53, obstacleImage));
        }

        playerCar.update(); // Oppdaterer spillerbilens posisjon

        // Beveger hindringer og sjekker kollisjoner
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obs = iterator.next();
            obs.moveDown(OBSTACLE_SPEED);
            if (obs.collidesWith(playerCar)) {
                gameOver(); // Spill over ved kollisjon
                return;
            }
            if (obs.getY() > HEIGHT) iterator.remove(); // Fjerner hindringer som har gått ut av skjermen
        }
    }

    private void drawBackground(GraphicsContext gc) {
        // Tegner bakgrunnsbilde
        gc.drawImage(highWayImage, 0, 0, WIDTH, HEIGHT);

        playerCar.draw(gc); // Tegner spillerens bil
        for (Obstacle obs : obstacles) obs.draw(gc); // Tegner hindringer

        // Viser overlevelsestid
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(20));
        gc.fillText("Time: " + elapsedTime + "s", 10, 20);

        // Viser "GAME OVER" melding hvis spillet er slutt
        if (!running) {
            gc.setFill(Color.RED);
            gc.setFont(new Font(40));
            gc.fillText("GAME OVER!", WIDTH / 2 - 100, HEIGHT / 3);
            restartButton.setVisible(true);
        }
    }

    private void gameOver() {
        running = false;
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        leaderboard.addScore(elapsedTime); // Legger til score i poengtavlen
        restartButton.setVisible(true);

        Platform.runLater(() -> {
            System.out.println("Game Over! Time survived: " + elapsedTime + " seconds");
            leaderboard.display(); // Viser poengtavlen
        });
    }

    private void restartGame(Stage stage) {
        start(stage); // Starter spillet på nytt
    }
}
