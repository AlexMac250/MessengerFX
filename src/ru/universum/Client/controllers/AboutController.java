package ru.universum.Client.controllers;

import javafx.scene.control.Label;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Александр on 11.12.2016.
 */
public class AboutController {
    public Label mainLabel;
    private final boolean[] isRand = {false};
    public void mouseEntered() {
        isRand[0] = true;
        new Thread(() -> {
            while (isRand[0]) {
                Random random = new Random();
                int r = random.nextInt(255);
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                try {
                    TimeUnit.MILLISECONDS.sleep(150);
                } catch (InterruptedException runnable) {
                    runnable.printStackTrace();
                }
                //mainLabel.setStyle("-fx-fill: rgb("+r+","+g+","+b+");");// FIXME: 12.12.2016
            }
            mainLabel.setStyle("-fx-fill:#4597F9;");
        }).start();
    }
    public void mouseExited(){
        isRand[0] = false;
    }

    public void actionListenerBtnClose(){
         Controller.hideAbout();
    }
}
