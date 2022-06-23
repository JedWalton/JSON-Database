package client;

import java.io.*;
import java.net.Socket;

public class Main {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 34522;

    public static void main(String[] args) {
        System.out.println("Client started!");
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())
        ) {
            output.writeObject(args);
            StringBuilder builder = new StringBuilder();
            for (String value : args) {
                builder.append(value).append(" ");
            }

            System.out.println("Sent: " + builder.toString().trim());
            System.out.println("Received: " + input.readUTF());
        } catch (IOException e) {
            System.out.println("Error! The server is offline.");
        }
    }
}
