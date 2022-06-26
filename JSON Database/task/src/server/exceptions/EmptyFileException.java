package server.exceptions;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException() {
        super("File is empty.");
    }
}
