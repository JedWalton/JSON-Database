package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        DatabaseController databaseController = new DatabaseController();


        Socket socket = getSocket();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        receivedFromClient(input);
        sendResponseToClient(output);

        databaseController.processUserInput();
    }

    private static void receivedFromClient(DataInputStream input) throws IOException {
        String readUTFInput = input.readUTF();
        System.out.println("Received: " + readUTFInput);
    }

    private static Socket getSocket() throws IOException {
        String address = "127.0.0.1";
        int port = 23456;
        ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
        System.out.println("Server started!");
        return server.accept();
    }

    private static void sendResponseToClient(DataOutputStream output) throws IOException {
        String serverResponse = "A record # N was sent!";
        System.out.print("Sent: " + serverResponse);
        output.writeUTF(serverResponse);
    }
}
