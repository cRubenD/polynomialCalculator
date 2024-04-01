package com.example.GraphicalUserInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // JavaFX initial setup
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Polynomial Calculator");
        stage.setResizable(false);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        ((WindowController)fxmlLoader.getController()).init(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}