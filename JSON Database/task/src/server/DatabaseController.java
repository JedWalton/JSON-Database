package server;

import server.utils.Commands;
import server.utils.UserInput;

import java.util.Arrays;

public class DatabaseController {

    public static String[] database;


    public DatabaseController() {
        database = new String[100];
        Arrays.fill(database, "");
    }


    public void processUserInput() {
        while (true) {
            String[] userInputLine = UserInput.getUserInputLine();
            String command = UserInput.getCommand(userInputLine);
            int id = UserInput.getId(userInputLine);
            String text = UserInput.getText(userInputLine);


            if (idInRange(id)) continue;

            commandLogic(command, id, text);
        }
    }

    private void commandLogic(String command, int id, String text) {
        if (command.equals("set")) {
            Commands.setCommand(id, text);
        } else if (command.equals("get")) {
            Commands.getCommand(id);
        } else if (command.equals("delete")) {
            Commands.deleteCommand(id);
        } else if (command.equals("exit")) {
            Commands.exit();
        }
    }

    private boolean idInRange(int id) {
        if (id > 99 || id < 0) {
            System.out.println("ERROR");
            return true;
        }
        return false;
    }
}
