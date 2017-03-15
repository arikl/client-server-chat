package com.arikl.chat.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by arikl on 3/15/17.
 */
public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * run server method
     * @throws UnknownHostException
     * @throws IOException
     */
    public void run() throws IOException {
        String nickname;
        // connect client to server
        Socket client = new Socket(host, port);
        System.out.println("Client successfully connected to server!");

        //Service excutor better for controlling the threads
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new ClientMessageHandler(client.getInputStream()));

        // ask for a nickname
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a nickname: ");
        nickname = sc.nextLine();

        // read messages from keyboard and send to server
        System.out.println("Send messages: ");
        PrintStream output=null;
        try{
            output = new PrintStream(client.getOutputStream());
            while (sc.hasNextLine()) {
                if(output!=null){
                    output.println(nickname + ": " + sc.nextLine());
                }
            }
        }
        catch(Exception e){
            e.getMessage();
        }
        finally {
            output.close();
            sc.close();
            client.close();
        }


    }
}
