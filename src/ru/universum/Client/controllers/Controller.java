package ru.universum.Client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Александр on 11.12.2016.
 */
public class Controller {
    static Stage stgLogin;
    static Stage stgRegister;
    static Stage stgSettings;
    static Stage stgAbout;

    public static void showLogin(MouseEvent event){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../fxml/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
        stgLogin = stage;
    }
    public static void hideLogin(){
        stgLogin.hide();
    }

    public static void showRegister(MouseEvent event){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../fxml/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
        stgRegister = stage;
    }
    public static void hideRegister(){
        stgRegister.hide();
    }

    public static void showSettings(MouseEvent event){

    }
    public static void hideSettings(){
        stgSettings.hide();
    }

    public static void showAbout(MouseEvent event){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../fxml/about.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
        stgAbout = stage;
    }
    public static void hideAbout(){
        stgAbout.hide();
    }
}
