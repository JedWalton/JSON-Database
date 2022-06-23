package server;

import com.beust.jcommander.JCommander;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    private static final int SERVER_PORT = 34522;

    public static void main(String[] args) {
        Database database = new Database(1000);
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            while (true) {
                try (
                        Socket socket = server.accept();
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String[] clientArgs = (String[]) input.readObject();
                    System.out.println("Received: " + Arrays.toString(clientArgs));

                    ArgsParser argsParser = new ArgsParser();
                    JCommander.newBuilder()
                            .addObject(argsParser)
                            .build()
                            .parse(clientArgs);

                    String requestType = argsParser.getRequestType();
                    int cellIndex = argsParser.getCellIndex();
                    String value = argsParser.getValue();
                    System.out.printf(
                            "Request type: %s\n" +
                                    "Cell index: %s\n" +
                                    "Value: %s\n", requestType, cellIndex, value);

                    switch (requestType) {
                        case ("set"):
                            output.writeUTF(database.set(cellIndex, value)); break;
                        case ("get"):
                            output.writeUTF(database.get(cellIndex)); break;
                        case ("delete"):
                            output.writeUTF(database.delete(cellIndex)); break;
                        case ("exit"):
                            System.exit(0);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
