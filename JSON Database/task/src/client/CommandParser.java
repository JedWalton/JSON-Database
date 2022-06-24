package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandParser {
    @Parameter(names = "-t", description = "the type of the request")
    private String requestType;
    @Parameter(names = "-k", description = "the index of the cell")
    private String indexOfCeil;
    @Parameter(names = "-v", description = "the value to save in the database")
    private String value;

    public CommandParser(String[] iCommand) {
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(iCommand);
    }

    public String serializeToJson() {
        Map<String, String> command = new LinkedHashMap<>();
        command.put("type", requestType);
        command.put("key", indexOfCeil);
        command.put("value", value);

        return new Gson().toJson(command);
    }
}
