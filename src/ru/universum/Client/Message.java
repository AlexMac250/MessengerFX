package ru.universum.Client;

public class Message {
    String command;
    String from;
    String message;
    boolean withDate = false;
    String date ;

    public Message(String command, String from, String message , boolean withDate) {
        this.command = command;
        this.from = from;
        this.message = message;
        this.withDate = withDate;
    }

    public Message(String command , String from , String date , String message) {
        this.command = command;
        this.from = from;
        this.message = message;
        this.date = date;
    }
    }
