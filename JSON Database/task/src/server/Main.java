package server;

import java.io.IOException;

public class Main {
    public static Server server;

    public static void main(String[] args) throws IOException {
        server = new Server(9889, "src/server/data/db.json");
        server.run();
    }
}
