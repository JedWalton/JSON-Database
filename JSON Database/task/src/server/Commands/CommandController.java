package server.Commands;

import com.google.gson.Gson;
import server.SimulatedDB;


public class CommandController {
    private BaseCommand baseCommand;
    private BaseCommand command;

    public BaseCommand getCommand() {
        return baseCommand;
    }

    public void createCommand(String request) {
        baseCommand = new Gson().newBuilder().excludeFieldsWithoutExposeAnnotation()
                .create().fromJson(request, BaseCommand.class);

        switch (baseCommand.type) {
            case "exit": {
                command = new ExitCommand();
                break;
            }
            case "set": {
                command = new SetCommand(baseCommand.key, baseCommand.value);
                break;
            }
            case "get": {
                command = new GetCommand(baseCommand.key);
                break;
            }
            case "delete": {
                command = new DeleteCommand(baseCommand.key);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setDataBase(SimulatedDB simulatedDB) {
        baseCommand.setDataBase(simulatedDB.getDataBase());
    }

    public String executeCommand() {
        return command.execute();
    }
}
