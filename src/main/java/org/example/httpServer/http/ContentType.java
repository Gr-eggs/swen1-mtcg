package org.example.httpServer.http;

public enum ContentType {
    PLAIN_TEXT("text/plain"),
    HTML("text/html"),
    JSON("application/json");

    public final String type;

    ContentType(String type) {
       this.type = type;
    }

    // Method to convert a String to a ContentType enum
    public static ContentType fromString(String type) {
        for (ContentType contentType : ContentType.values()) {
            if (contentType.type.equalsIgnoreCase(type)) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("Unknown content type: " + type);
    }
}
