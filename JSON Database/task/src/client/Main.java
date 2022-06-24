package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 6991;

    public static void main(String[] args) {
        String command = new CommandParser(args).serializeToJson();
        System.out.println("Client started!");

        try (Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            output.writeUTF(command);
            System.out.println("Sent: " + command);
            System.out.println("Received: " + input.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
