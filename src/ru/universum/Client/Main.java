package ru.universum.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.universum.Client.controllers.Controller;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("resources/fxml/main_menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("NEOnline");
        primaryStage.setResizable(false);
        assert root != null;
        primaryStage.setScene(new Scene(root, 200, 250));
        Controller.stgMainMenu = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        Client.account.friends.add(new Friend(5, "test_friend"));
        launch(args);
    }
}
