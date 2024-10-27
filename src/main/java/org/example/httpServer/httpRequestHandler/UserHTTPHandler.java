package org.example.httpServer.httpRequestHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dto.CreateUserDto;
import org.example.dto.LoginUserDto;
import org.example.exceptions.EntityAlreadyExistsException;
import org.example.httpServer.http.ContentType;
import org.example.httpServer.http.HttpStatus;
import org.example.httpServer.server.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.httpServer.server.Response;
import org.example.models.User;

public class UserHTTPHandler {
    private final UserStorage storage;

    public UserHTTPHandler(UserStorage storage){
        this.storage = storage;
    }
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON-Parser for Jackson

    public Response loginUser(Request request){
        try{
            var user = objectMapper.readValue(request.getPayload(), LoginUserDto.class);  // JSON zu User-Objekt parsen

            var storedUser = storage.login(user);

            if(storedUser != null){
                return new Response(HttpStatus.OK, ContentType.PLAIN_TEXT, storedUser.getToken());
            }
            return new Response(HttpStatus.UNAUTHORIZED, ContentType.PLAIN_TEXT, "Invalid username/password provided");

        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, "Failed to create user, invalid payload");
        }
    }

    public Response createUser(Request request){
        try{
            var user = objectMapper.readValue(request.getPayload(), CreateUserDto.class);  // JSON zu User-Objekt parsen

            storage.addUser(new User(user.getUsername(), user.getPassword()));

            return new Response(HttpStatus.CREATED, ContentType.PLAIN_TEXT, "User successfully created");
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            return new Response(HttpStatus.BAD_REQUEST, ContentType.PLAIN_TEXT, "Failed to create user, invalid payload");
        }catch( EntityAlreadyExistsException e){
            System.err.println(e.getMessage());
            return new Response(HttpStatus.CONFLICT, ContentType.PLAIN_TEXT, "User with same username already registered");
        }
    }
}
