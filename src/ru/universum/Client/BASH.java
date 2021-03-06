package ru.universum.Client;

import java.util.Scanner;

class BASH extends Thread{
    private Scanner scan = new Scanner(System.in);
    @Override
    public void run() {
        System.out.println("BASH v1.0");
        System.out.print(">> ");
        while (!interrupted()) command(scan.nextLine());
    }

    private void command(String command){
        String[] line = decrypt(command);
        switch (line[0]){

            case "connect":
                Client.connect();
                break;

            case "login":
                Client.login(line[1], line[2]);
                break;

            case "addFriend":
                Client.execute(new String[]{line[0],line[1], line[2]});
                break;

            case "exit":
                System.exit(2);
                break;

            case "findByNick":
                if (Client.statusConnected) Client.execute(line);
                else {
                    System.err.println("Not connected!");
                    System.out.println("Connect? (y/n)");
                    System.out.print("> ");
                    String ans = new Scanner(System.in).next();
                    if (ans.equals("y")){
                        Client.connect();
                        Client.execute(line);
                    } else {
                        if (ans.equals("n")){
                            System.out.println("Okay, bro! ;)");
                        }
                    }
                }

                break;

            case "editIP":
                Client.HOSTNAME = line[1];
                System.out.println("Modified IP: "+line[1]);
                break;

            case "egg":
                System.out.println("\n||\\  || ||̄ ̄ ̄ ̄  //̄ ̄ ̄ ̄ \\\\\n||\\\\ || ||____ ||    ||\n|| \\\\|| ||̄ ̄ ̄ ̄  ||    ||\n||  \\|| ||____ \\\\____//nline\n\nHave a nice day, bro! ;)\n");
                break;

            case "ipconfig":
                System.out.println("IP configuration:");
                System.out.println((Client.HOSTNAME.contains(":") ? "IPv6: " : "IPv4: ")+ Client.HOSTNAME);
                System.out.println("MainPort: 2905");
                System.out.println("PrivatePort: "+ Client.port);
                System.out.println();
                break;

            default:
                System.err.println("Command \""+ line[0]+"\" not found!");
                break;
        }
        System.out.print(">> ");
    }

    private static String[] decrypt(String message){
        String[] s = new String[4];
        char[] c = message.toCharArray();
        int i = 0;
        s[0] = "";
        for (char ch : c){
            if((ch == ' ')){
                i++;
                s[i] = "";
            }else{
                s[i]+=(ch);
            }
        }
        return s;
    }
}
