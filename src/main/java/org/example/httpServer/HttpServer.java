package org.example.httpServer;

import org.example.httpServer.httpRequestHandler.UserStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.example.httpServer.httpRequestHandler.ClientHandler.handleClient;

public class HttpServer {
    private final int port;

    private final UserStorage userStorage = new UserStorage();
    public HttpServer(int port){
        this.port = port;
    }

    public void createServerSocket(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port "+port);

            while (true) {
                // Accept an incoming connection
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Handle the request in a new thread
                new Thread(() -> handleClient(socket, userStorage)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
