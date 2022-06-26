package server.exceptions;

public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException() {
        super("Key not found.");
    }
}
