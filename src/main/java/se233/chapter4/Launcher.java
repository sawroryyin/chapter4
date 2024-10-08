package se233.chapter4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter4.controller.DrawingLoop;
import se233.chapter4.controller.GameLoop;
import se233.chapter4.view.GameStage;

public class Launcher extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        GameStage gameStage = new GameStage();
        GameLoop gameLoop = new GameLoop(gameStage); //Added later
        DrawingLoop drawingLoop = new DrawingLoop(gameStage);
        Scene scene = new Scene(gameStage, gameStage.WIDTH, gameStage.HEIGHT);
        scene.setOnKeyPressed(event -> gameStage.getKeys().add(event.getCode()));
        scene.setOnKeyReleased(event -> gameStage.getKeys().remove(event.getCode()));
        primaryStage.setTitle("Mario");
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread gameLoopThread = new Thread(gameLoop);
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();

        Thread drawingLoopThread = new Thread(drawingLoop);
        drawingLoopThread.setDaemon(true);
        drawingLoopThread.start();
    }
}