package server.Commands;

import com.google.gson.annotations.Expose;

import java.util.Map;

public class BaseCommand implements Command {
    @Expose
    protected String type;
    @Expose
    protected String key;
    @Expose
    protected String value;
    protected static Map<String, String> dataBase;

    public String getType() {
        return this.type;
    }

    protected void setDataBase(Map dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String execute() {
        return printError();
    }
}
