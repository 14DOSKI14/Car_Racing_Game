package com.example.carracinggame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Setter spillikonet
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/gameicon.png")));

        // Viser en "GET READY!!"-tekst mens spillet laster
        Text loadingText = new Text("GET READY!!");
        loadingText.setFont(Font.font(20));
        StackPane root = new StackPane(loadingText);
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.setTitle("Car Racing Game");
        primaryStage.show();

        // Simulerer lastetid og starter spillet
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Venter i 2 sekunder for å simulere lastetid
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Starter spillet etter lastetiden
            Platform.runLater(() -> new Game().start(primaryStage));
        }).start();
    }
}
