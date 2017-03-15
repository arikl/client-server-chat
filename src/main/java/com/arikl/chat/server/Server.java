package com.arikl.chat.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by arikl on 3/15/17.
 */
public class Server {
    private int port;
    private List<PrintStream> clients;
    private ServerSocket server;
    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<PrintStream>();
    }


    /**
     * Running server methods
     * @throws IOException
     */
    public void run() throws IOException {
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        System.out.println("Port is now open.");

        while (true) {
            // accepts a new client
            Socket client = server.accept();
            System.out.println("Connection established with client: " + client.getInetAddress().getHostAddress());

            // add client message to list
            this.clients.add(new PrintStream(client.getOutputStream()));

            // create a new thread for client handling
            //Service excutor better for controlling the threads
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(new ClientHandler(this, client.getInputStream()));
        }
    }

    void broadcastMessages(String msg) {
        for (PrintStream client : this.clients) {
            client.println(msg);
        }
    }
}
