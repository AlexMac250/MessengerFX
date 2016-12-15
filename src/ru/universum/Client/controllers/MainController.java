package ru.universum.Client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import ru.universum.Client.Client;
import ru.universum.Client.Friend;
import java.util.HashMap;
import java.util.Map;

import ru.universum.Client.resources.values.COLORS;

/**
 * Created by Александр on 13.12.2016.
 */
public class MainController {


    static Map<Integer, PanFriend> panFriendList = new HashMap<>();
    static Map<Integer, Tab> tabs = new HashMap<>();
    public static GridPane pane;
    public static void loadFriends(){
        final int FRIENDS = Client.account.friends.size();
        Rectangle rect = null;
        Button button = null;
        int i = 0;
        for (Friend friend : Client.account.friends) {
            System.out.println(friend.login);
            int maxLength = 12;
            String login = friend.login;
            button = new Button();
            Tooltip tooltipBtn = new Tooltip();
            rect = getStatusImage(friend.isOnline);
            if (login.length() > maxLength) {
                tooltipBtn.setText(login);
                login = login.substring(0, maxLength - 1) + "...";
            }
            tooltipBtn.setGraphic(button);
            button.setText(login);

            pane.add(rect, 0, i);
            pane.add(button, 1, i);
            panFriendList.put(friend.id, new PanFriend(button, rect));
            i++;
        }
//      GridBagLayoutManager(panFriends, checkBoxOnline, GridBagConstraints.CENTER, 0, i + 1, 1);
//      GridBagLayoutManager(panFriends, button, GridBagConstraints.HORIZONTAL, 1, i + 1, 1);
//      if (FRIENDS > 13) GridBagLayoutManager(panFriends, new JLabel("    "), GridBagConstraints.HORIZONTAL, 2, i + 1, 1);

//      button.addActionListener(e -> {
//          currentFriend = friend;
//          System.out.println("friend selected");
//          System.out.println(currentFriend.toString());
//          setDialog(friend);
//          createTab(friend);
//          tabbedPane.setSelectedIndex(tabs.get(friend.id).count);
//      });
//
//      System.out.println(friend.toString());
//      }
//        КНОПКА ДОБАВЛЕНИЯ В ДРУЗЬЯ
//        JButton button = new JButton("+");
//        button.setForeground(MAIN_COLOR);
//        button.setFont(new Font(FONT_style, Font.BOLD, button.getFont().getSize() + 6));
//        GridBagLayoutManager(panFriends, button, GridBagConstraints.HORIZONTAL, 0, FRIENDS + 1, 2);
//
//        button.addActionListener(e -> {
//            System.out.println(panFriends.getWidth());
//            new FindFriend().showFrame();
//        });
//        frame.setVisible(false);
//        frame.setVisible(true);
    }

    public static Rectangle getStatusImage(boolean isOnline){
        Rectangle rect = new Rectangle(12, 12);
        rect.setRotate(45);
        rect.setStyle("-fx-background-color: "+(isOnline ? COLORS.online : COLORS.offline));
        rect.setEffect(new Bloom(0.3));
        return rect;
    }
    static class PanFriend{
        Button button;
        Rectangle isOnline;

        PanFriend(Button button, Rectangle isOnline) {
            this.button = button;
            this.isOnline = isOnline;
        }
    }
}
