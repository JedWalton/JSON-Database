package server.Commands;

public class ExitCommand extends BaseCommand {

    @Override
    public String execute() {
        return printOK();
    }
}
