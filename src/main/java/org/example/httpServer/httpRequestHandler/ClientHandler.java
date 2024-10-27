package org.example.httpServer.httpRequestHandler;


import org.example.httpServer.http.ContentType;
import org.example.httpServer.http.Method;
import org.example.httpServer.server.Request;

import java.io.*;
import java.net.Socket;

import static org.example.httpServer.server.Request.parseMethod;
import static org.example.httpServer.server.Request.parsePath;

public class ClientHandler {

    public static void handleClient(Socket socket, UserStorage storage) {
        try {
            // Read the request from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Parse the HTTP request
            Request request = new Request();

            String requestLine = in.readLine();
            if (requestLine == null) {
                return;
            }
            request.setMethod(parseMethod(requestLine));
            request.setPathname(parsePath(requestLine));


            // Read headers and request body
            String line;
            int contentLength = 0;
            while (!(line = in.readLine()).isEmpty()) {
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }else if (line.startsWith("Content-Type:")) {
                    ContentType parsedContentType = ContentType.fromString( line.split(":")[1].trim());
                    request.setContentType(parsedContentType);
                }
            }

            String payload = null;
            if (contentLength > 0) {
                char[] bodyChars = new char[contentLength];
                in.read(bodyChars);
                payload = new String(bodyChars);  // JSON-Body als String lesen
            }
            request.setPayload(payload);

            /*
            System.out.println("Received request: " + requestLine);
            System.out.println("Received : " + request.getMethod());
            System.out.println("Received : " + request.getPathname());
            System.out.println("Received : " + request.getContentType());
            System.out.println("Received : " + request.getPayload());
            */


            if(request.getPathname().equals("/users") && request.getMethod().equals(Method.POST)){
                UserHTTPHandler handler = new UserHTTPHandler(storage);
                var response = handler.createUser(request);
                out.write(response.get());
                out.flush();
                socket.close();
            }else if(request.getPathname().equals("/sessions")  && request.getMethod().equals(Method.POST)){
                var handler = new UserHTTPHandler(storage);
                var response = handler.loginUser(request);
                out.write(response.get());
                out.flush();
                socket.close();
            }else{
                socket.close();
            }
        }catch ( IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
