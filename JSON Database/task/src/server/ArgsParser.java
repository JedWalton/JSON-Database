package server;

import com.beust.jcommander.Parameter;

public class ArgsParser {
    @Parameter(names = "-t", description = "Type of the request")
    private String requestType;

    @Parameter(names = "-i", description = "Index of the cell")
    private int cellIndex;

    @Parameter(names = "-m", description = "Value to save in the database")
    private String value = "";

    public String getRequestType() {
        return requestType;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public String getValue() {
        return value;
    }
}
