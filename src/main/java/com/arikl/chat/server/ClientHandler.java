package com.arikl.chat.server;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by arikl on 3/15/17.
 */
public class ClientHandler implements Runnable {
    private Server server;
    private InputStream client;

    public ClientHandler(Server server, InputStream client) {
        this.server = server;
        this.client = client;
    }

    public void run() {
        String message;

        // when there is a new message, broadcast to all
        Scanner sc = new Scanner(this.client);
        while (sc.hasNextLine()) {
            message = sc.nextLine();
            server.broadcastMessages(message);
        }
        sc.close();
    }
}
