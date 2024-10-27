package org.example.httpServer.httpRequestHandler;

import org.example.dto.LoginUserDto;
import org.example.exceptions.EntityAlreadyExistsException;
import org.example.models.User;
import org.example.models.UserWithToken;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private final Map<String, UserWithToken> userMap = new HashMap<>();

    public UserStorage(){}
    public synchronized UserWithToken addUser(User user) throws EntityAlreadyExistsException {
        if(userMap.get(user.getUsername()) == null){
            String token = user.getUsername()+"-mtcgToken";
            UserWithToken safeUserToken =  new UserWithToken(user.getUsername(), hashWithSHA256(user.getPassword()), token);
            userMap.put(user.getUsername(), safeUserToken);
            return safeUserToken;
        }
        throw new EntityAlreadyExistsException("User with username "+user.getUsername()+" already exists");
    }

    public UserWithToken login(LoginUserDto loginDto) {
        String hashedPassword = hashWithSHA256(loginDto.getPassword());

        UserWithToken foundUser = userMap.get(loginDto.getUsername());
        if(foundUser != null && foundUser.getPassword().equals(hashedPassword)){
            return foundUser;
        }
        return null;
    }

    public static String hashWithSHA256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convert the input string to bytes and hash it
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the hashed bytes to a hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

}
