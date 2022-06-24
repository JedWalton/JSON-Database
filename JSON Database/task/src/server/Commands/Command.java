package server.Commands;

public interface Command {

    String execute();

    default String printError() {
        return "{\"response\":\"ERROR\",\"reason\":\"No such key\"}";
    }

    default String printOK() {
        return "{\"response\":\"OK\"}";
    }
}
