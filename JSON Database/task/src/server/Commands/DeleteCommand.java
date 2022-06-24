package server.Commands;

public class DeleteCommand extends BaseCommand {

    DeleteCommand(String key) {
        this.key = key;
    }

    @Override
    public String execute() {
        if (!dataBase.containsKey(key)) {
            return printError();
        } else {
            dataBase.remove(key);
            return printOK();
        }
    }
}
