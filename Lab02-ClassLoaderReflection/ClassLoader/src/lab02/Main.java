package lab02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    static App app = new App();

    public static void main(String[] args) throws IOException {



        System.out.println("Start...");




        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket welcomeSocket = null;
                String clientText = null;

                try {
                    welcomeSocket = new ServerSocket(4000);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    Socket connectionSocket = null;
                    try {
                        connectionSocket = welcomeSocket.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Ready ");

                    DataInputStream inFromClient = null;
                    try {
                        inFromClient = new DataInputStream(connectionSocket.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        clientText = inFromClient.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Recived " + clientText);
                }
            }
        });
        server.start();

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                app.loadUpper();
            }

        });
        t.start();


        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    app.loadExla();
                } catch (NoSuchMethodException | ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
        t2.start();

        System.out.print("Enter something:");

        Scanner scanner = new Scanner(System. in);
        String input=scanner.nextLine();


        Socket outputSocket = null;
        try {
            outputSocket = new Socket("localhost", 2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream outToPrint = null;
        try {
            outToPrint = new DataOutputStream(outputSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BufferedReader inFromServer=new BufferedReader(new InputStreamReader(outputSocket.getInputStream()));
        //input=inFromUser.readLine();
        try {
            outToPrint.writeUTF(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Sending " + input);


    }


}


