package ru.universum.Client.controllers;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import ru.universum.Client.Client;
import ru.universum.Client.Security;

public class LoginController {
    public TextField loginField;
    public PasswordField passwordField;
    public CheckBox remAccData;
    public Label labelInfo;

    public Label getLabelInfo(){
        return labelInfo;
    }

    public void login(){
        Controller.hideMainMenu();
        Controller.hideLogin();
        Controller.showMain();
        MainController.loadFriends();
        //        if (loginField.getText().length() > 1 & passwordField.getText().length() > 1) {
//                    labelInfo.setText("Входим...");
//                    if (!Client.statusConnected) Client.connect();
//                    Client.login(loginField.getText(), Security.getSHA(passwordField.getText()));
//        } else {
//
//
//
//            if (loginField.getText().length() < 1 & passwordField.getText().length() > 0){ //login
//                ((DropShadow) loginField.getEffect()).setColor(Color.web("#990000"));
//                labelInfo.setText("Введите логин!");
//            } else if (loginField.getText().length() > 0 & passwordField.getText().length() < 1){ //password
//                ((DropShadow) passwordField.getEffect()).setColor(Color.web("#990000"));
//                labelInfo.setText("Введите пароль!");
//            } else if (loginField.getText().length() < 1 & passwordField.getText().length() < 1){ //login & password
//                ((DropShadow) loginField.getEffect()).setColor(Color.web("#990000"));
//                ((DropShadow) passwordField.getEffect()).setColor(Color.web("#990000"));
//                labelInfo.setText("Введите данные!");
//
//            }
//        }
    }

    public void setDefColorTextField(){
        ((DropShadow) loginField.getEffect()).setColor(Color.web("#2F5D90"));
        ((DropShadow) passwordField.getEffect()).setColor(Color.web("#2F5D90"));
        labelInfo.setText("Введите данные и нажмите Войти");
    }
}
