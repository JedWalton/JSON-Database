package server;

import java.util.Arrays;

public class Database {
    private final String[] database;
    private static final String successMessage = "OK";
    private static final String errorMessage = "ERROR";

    public Database(int size) {
        this.database = new String[size];
        Arrays.fill(this.database, "");
    }

    public String get(int index) {
        if (index - 1 >= 0 && index - 1 < database.length && !database[index - 1].equals("")) {
            return database[index - 1];
        } else {
            return errorMessage;
        }
    }

    public String set(int index, String value) {
        if (index - 1 >= 0 && index - 1 < database.length) {
            database[index - 1] = value;
            return successMessage;
        } else {
            return errorMessage;
        }
    }

    public String delete(int index) {
        if (index - 1 >= 0 && index - 1 < database.length) {
            database[index - 1] = "";
            return successMessage;
        } else {
            return errorMessage;
        }
    }
}
