package com.arikl.chat.server;

import java.io.IOException;

/**
 * Created by arikl on 3/15/17.
 */
public class MainServer {

    public static void main(String[] args) throws IOException {
        new Server(12345).run();
    }
}
