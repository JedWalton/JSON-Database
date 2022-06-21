package server.utils;

import server.exception.CommandNotFoundException;

import java.util.Scanner;

public class UserInput {
    private UserInput() {
    }

    public static String getCommand(String[] userInputLine) {
        String commandString = userInputLine[0];

        switch (commandString) {
            case "get":
                return "get";
            case "set":
                return "set";
            case "delete":
                return "delete";
            case "exit":
                return "exit";
        }

        throw new CommandNotFoundException();
    }

    public static int getId(String[] userInputLine) {
        if (userInputLine.length>1) {
            return Integer.parseInt(userInputLine[1]) - 1;
        }
        return 0;
    }

    public static String getText(String[] userInputLine) {
        String text = "";
        if (userInputLine.length > 2) {
            for (int i = 2; i < userInputLine.length; i++) {
                text = text.concat(userInputLine[i]);
                if (i < userInputLine.length - 1) {
                    text = text.concat(" ");
                }
            }
        }
        return text;
    }

    public static String[] getUserInputLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().split(" ");
    }
}
