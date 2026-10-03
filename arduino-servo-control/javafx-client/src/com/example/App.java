package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Главный класс приложения JavaFX.
 * Загружает интерфейс из файла MainView.fxml.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Arduino Uno Servo Controller - Панель управления");
        primaryStage.setScene(new Scene(root, 400, 360));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
