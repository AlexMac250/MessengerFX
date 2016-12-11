package ru.universum.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("./fxml/main_menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
