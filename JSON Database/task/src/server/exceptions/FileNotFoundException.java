package server.exceptions;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException() {
        super("File not found.");
    }
}
