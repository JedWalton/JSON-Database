package server.Commands;

public class SetCommand extends BaseCommand {

    SetCommand(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String execute() {
        dataBase.put(key,value);
        return printOK();

    }
}
