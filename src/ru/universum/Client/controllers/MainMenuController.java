package ru.universum.Client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void actionListenerBtnLogin(MouseEvent event){
        Starter.showLogin(event);
    }

    public void actionListenerBtnRegister(MouseEvent event){

    }
}
