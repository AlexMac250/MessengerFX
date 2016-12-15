package ru.universum.Client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.universum.Client.Client;

import java.io.IOException;

/**
 * Created by Александр on 11.12.2016.
 */
public class Controller {
    public static Stage stgMainMenu;
    static Stage stgMain;
    static Stage stgLogin;
    static Stage stgRegister;
    static Stage stgSettings;
    static Stage stgAbout;
    static Stage stgMessage;

    public static void hideMainMenu(){
        stgMainMenu.hide();
    }
    public static void showMain(){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../resources/fxml/main.fxml"));
        } catch (IOException e) {
        e.printStackTrace();
    }
        stage.setScene(new Scene(root));
        stage.setTitle(Client.account.login+" | NEOnline "+Client.client_version);
        stage.show();
    }
    public static void hideMain(){
        stgMain.hide();
    }

    public static void showLogin(MouseEvent event){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../resources/fxml/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Аутентификация");
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
            root = FXMLLoader.load(Controller.class.getResource("../resources/fxml/register.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Регистрация");
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

    public static void showAbout(MouseEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../resources/fxml/about.fxml"));
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

    @FXML static Label messageLab;
    public static void showInfo(MouseEvent event, String message){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Controller.class.getResource("../resources/fxml/message.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Сообщение!");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        messageLab.setText(message);
        stage.show();
        stgMessage = stage;

    }
}
