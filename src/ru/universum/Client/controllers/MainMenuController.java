package ru.universum.Client.controllers;

import javafx.scene.input.MouseEvent;

public class MainMenuController {

    public void actionListenerBtnLogin(MouseEvent event){
        Controller.showLogin(event);
    }

    public void actionListenerBtnRegister(MouseEvent event){
        Controller.showRegister(event);
    }

    public void actionListenerBtnSettings(MouseEvent event){

    }

    public void actionListenerBtnAbout(MouseEvent event){

    }
}
