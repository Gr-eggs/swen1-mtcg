package org.example.httpServer.server;

public interface Service {
    Response handleRequest(Request request);
}
