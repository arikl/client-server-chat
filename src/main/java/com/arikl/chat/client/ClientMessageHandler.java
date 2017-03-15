package com.arikl.chat.client;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by arikl on 3/15/17.
 */
public class ClientMessageHandler implements Runnable{

    private InputStream server;

    public ClientMessageHandler(InputStream server) {
        this.server = server;
    }
    public void run() {
        // receive server messages and print out to screen
        Scanner s = new Scanner(server);
        try{
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        }
        catch(Exception e){
            e.getMessage();
        }
        finally{
            s.close();
        }


    }
}
