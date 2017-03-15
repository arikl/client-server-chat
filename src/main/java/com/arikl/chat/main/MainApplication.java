package com.arikl.chat.main;

import com.arikl.chat.client.Client;
import com.arikl.chat.server.Server;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by arikl on 3/15/17.
 */
public class MainApplication {
    public static final String SERVER = "server";
    public static final int PORT = 12345;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        try {
            System.out.println("Please type what are you ?:");
            System.out.println("server");
            System.out.println("client");
            System.out.println("Please choose:");
            Scanner sc = new Scanner(System.in);
            String type = sc.next();
            if (type.equals(SERVER)) {
                new Server(PORT).run();

            } else {
                new Client(HOST, PORT).run();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught");
        }
    }
}
