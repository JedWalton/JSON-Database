package server;

import java.util.HashMap;
import java.util.Map;

public class SimulatedDB {
    private final Map<String, String> dataBase = new HashMap();

    public Map getDataBase() {
        return dataBase;
    }
}
