package server.utils;

import server.DatabaseController;

import java.util.Objects;

public class Commands extends DatabaseController {

    private Commands() {

    }

    public static void setCommand(int id, String text) {
        database[id] = text;
        System.out.println("OK");
    }

    public static void getCommand(int id) {
        String text = database[id];

        if (Objects.equals(text, "")) {
            System.out.println("ERROR");
        } else {
            System.out.println(text);
        }
    }

    public static void deleteCommand(int id) {
        database[id] = "";
        System.out.println("OK");
    }

    public static void exit() {
        System.exit(1);
    }
}
