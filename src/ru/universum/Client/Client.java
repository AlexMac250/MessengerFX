package ru.universum.Client;


import ru.universum.Client.controllers.LoginController;
import ru.universum.Client.controllers.Controller;
import ru.universum.Client.controllers.RegisterController;
//import ru.universum.Loader.Account;
//import ru.universum.Loader.Friend;
//import ru.universum.Loader.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {
    static final String java_version = System.getProperty("java.version");
    public static final String os_name = System.getProperty("os.version");
    static final String os_arch = System.getProperty("os.arch");
    static final String os_version = System.getProperty("os.version");
//    static final String working_directory = Util.getWorkingDirectory().getAbsolutePath();

    static final String client_version = "version 1.0 alpha 1";
    private static List<ClientMessage> messages = new ArrayList<>();
    static Socket socket;
    static int port;
    private static DataOutputStream os;
    private static InputReader reader;

    private static final boolean NODATE = false;
    private static final boolean DATED = true;
    static String HOSTNAME = "95.154.89.186";

    static Account account = new Account();

    private static boolean statusLogged = false;
    public static boolean statusConnected = false;
    private static boolean statusRegistered = false;

    static LoginController LoginController = new LoginController();
    static RegisterController RegisterController = new RegisterController();
    static ArrayList<Account> usersInSearch = new ArrayList<>();//есть только id и login
    static Map<Integer , Dialog> dialogs = new HashMap<>();
    public static ArrayList<Account> getUsersInSearch(){
        return usersInSearch;
    }
    //FIXME update to Dialogs

    public static void connect(){
        try {
            socket = new Socket(InetAddress.getByName(HOSTNAME), 2905);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            port = is.readInt();
            System.out.println("got port");
            socket.close();
            is.close();
            TimeUnit.MILLISECONDS.sleep(150);
            socket = new Socket(InetAddress.getByName(HOSTNAME), port);
            System.out.println("Connected to port " + port);
            statusConnected = true;
            os = new DataOutputStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            //читает входящие комманды
            reader = new InputReader(is);
            reader.setDaemon(true);
            reader.start();
        } catch (Exception e) {
            javafx.scene.control.Label label = LoginController.getLabelInfo();
            label.setText("Нет соединения! :(");
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        statusConnected = false;
        port = 0;
        account = new Account();
        usersInSearch = new ArrayList<>();
        messages = new ArrayList<>();
        dialogs = new HashMap<>();
    }

    public static void login(String login, String password){
        send(new Message("login",login,password,NODATE));
    }

    public static void register(String login, String password){
        send(new Message("register",login,password,NODATE));
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException ignored) {}
        if (statusRegistered){
            login(login, password);
            Controller.hideRegister();
        } else {
            RegisterController.getLabelInfo().setText("Ошибка регистрации");
        }
    }

    public static void resOfFriend(String ans, int id){
        execute(descript("resOfFriend$"+id+"$"+ans));
    }

    private static void writeMessage(String from, String date, String message){

    }
    public static void execute(String[] command){
        switch (command[0]){
            // второй аргумент - логин третий пароль
            case "login" :
                send(new Message(command[0],command[1],command[2] , NODATE));
                break;

            //так же как и в логине
            case "register" :
                send(new Message(command[0], command[1], command[2] , NODATE));
                break;

            case "send" :
                //первый аргумент - send , второй - idTO , третий сам текст , на сеерв отправляется 4 аргумента где третий это дата а четвертый это текст.
                send(new Message(command[0], command[1], command[2] , DATED));
                break;

            case "message" :
                //принял входящее сообщеине
                dialogs.get(Integer.parseInt(command[1])).addMes(new ClientMessage(command[1], command[2], command[3]));
                writeMessage(command[1], command[2], command[3]);
                break;

            //заполняет френдов с сервера.
            case "friend" :
                addFriend(command);
                break;

            case "account" :
                account.login = command[2];
                account.id = Integer.parseInt(command[3]);
//                Frames.MainFrame.showFrame();
                // отображаешь майн фрейм
                break;

            case "logged" :
                if(command[2].equals("true")){
                    statusLogged = true;
                    Controller.hideLogin();
                    //залогинился
                }else{
                    LoginController.getLabelInfo().setText("Не верные логин или пароль!");
                }
                break;

            //ну тут понятно
            case "connection" :
                LoginController.getLabelInfo().setText("Соединение потеряно!");
                RegisterController.getLabelInfo().setText("Соединение потеряно!");
//                Frames.MainFrame.setInfo("Соединение потеряно!", Color.RED);
                statusLogged = false;
                statusConnected = true;
                System.out.println("[ERROR]: Connection refused");
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
                break;

            case "addFriend" :
                send(new Message(command[0],command[1], command[2], NODATE));
                break;

            //если ошибка при добавлении в друзья
            case "noFriended" :
//                Frames.MainFrame.setInfo("Ваше предложение в друзья "+command[2]+", отменено.", Color.BLACK);
                //ЗДЕСЬ СДЕЛАЙ ВЫВОД ТОГО ЧТО ЕГО ПРЕДЛОЖЕНИЕ В ДРУЗЬЯ ПРОИГНОРИЛ ПОЛЬЗОВАТЕЛЬ С НИКОМ command[1]
                break;

            case "online" :
                for (Friend fr : account.friends){
                    if(fr.id == Integer.parseInt(command[2])){
//                        Frames.MainFrame.panFriendList.get(fr.id).isOnline.setSelected(true);
//                        Frames.MainFrame.panFriendList.get(fr.id).isOnline.setToolTipText("Онлайн");
                        break;
                    }
                }
                break;

            case "offline" :
                for (Friend fr : account.friends){
                    if(fr.id == Integer.parseInt(command[2])) {
//                        Frames.MainFrame.panFriendList.get(fr.id).isOnline.setSelected(false);
//                        Frames.MainFrame.panFriendList.get(fr.id).isOnline.setToolTipText("Оффлайн");
                        break;
                    }
                }
                break;

            //доделай сам
            case "askToFriend" :
//                Frames.new AddFriend(new Friend(Integer.parseInt(command[2]), command[3]));
                //console.log("Accept friend :" +command[3] + " ?");
                break;

            case "registered" :
                if(command[2].equals("true")){
                    statusRegistered = true;
                }
                break;

            case "resOfFriend" :
                //resOfFriend id answer
                send(new Message(command[0],command[1],command[2],NODATE));
                break;

            //при первом запросе юзеров
            case "getUsers" :
                send(new Message(command[0],"","",NODATE));
                break;

            //при 2м+ запросе
            case "get20More" :
                send(new Message(command[0],"","",NODATE));
                break;

            case "user" :
                usersInSearch.add(new Account(command[1]/*+(command[1].equals(account.login) ? " (Вы)" : "")*/,Integer.parseInt(command[2]),Boolean.parseBoolean(command[3])));
                System.out.println(usersInSearch.get(usersInSearch.size()-1).login + " " + usersInSearch.get(usersInSearch.size()-1).id);
                break;

            case "findByNick" :
                send(new Message(command[0],command[1],"",NODATE));
                break;
        }
    }
    private static void addFriend(String[] args){
        if(!Objects.equals(args[2], "null")){
            account.friends.add(new Friend(Integer.parseInt(args[2]),args[3]));
            dialogs.put(account.friends.get(account.friends.size()-1).id, new Dialog(account.friends.get(account.friends.size()-1)));
//            if (Frames.MainFrame.isInit){
//                Frames.MainFrame.loadFriends();
//            }
        }else{
//            console.log("No friends", "m");
        }
    }
    static String[] descript(String message){
        String[] s = new String[4];
        char[] c = message.toCharArray();
        int i = 0;
        s[0] = "";
        for (char ch : c){
            if(s[0].equals("send")& i ==2){
                s[i]+=(ch);
            }else{
                if((ch == '$' | ch == '[' | ch == ']')& i!=3){
                    i++;
                    s[i] = "";
                }else{
                    s[i]+=(ch);
                }
            }
        }
        return s;
    }
    private static void send(Message message){
        try {
            os = new DataOutputStream(socket.getOutputStream());
            os.writeUTF(message.toString());
            os.flush();
        } catch (IOException e) {
//            console.log("Lost connection", "exc");
            statusConnected = false;
            statusLogged = true;
        }
    }
}