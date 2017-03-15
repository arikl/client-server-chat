package com.arikl.chat.client;

import com.arikl.chat.server.Server;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by arikl on 3/15/17.
 */
public class MainClient {

    public static final String SERVER = "server";

    public static void main(String[] args) throws IOException {

        new Client("127.0.0.1", 12345).run();

    }

}
