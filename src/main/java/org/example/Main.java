package org.example;

import org.example.httpServer.HttpServer;

public class Main {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(10001);
        server.createServerSocket();
    }
}
