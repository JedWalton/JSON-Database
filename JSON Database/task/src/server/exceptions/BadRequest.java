package server.exceptions;

public class BadRequest extends RuntimeException{
    public BadRequest() {
        super("Bad request.");
    }
}
