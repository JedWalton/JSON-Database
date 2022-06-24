package server.Commands;

public class GetCommand extends BaseCommand {

    private String value;

    GetCommand(String key) {
        this.key = key;
    }

    @Override
    public String printOK() {
        return "{\"response\":\"OK\",\"value\":\"" + value + "\"}";
    }

    @Override
    public String execute() {
        if (!dataBase.containsKey(key)) {
            return printError();
        } else {
            value = dataBase.get(key);
            return printOK();
        }
    }
}
