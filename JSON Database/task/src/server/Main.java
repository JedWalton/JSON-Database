package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            System.out.println(arg);
        }
        DatabaseController databaseController = new DatabaseController();


        String address = "127.0.0.1";
        int port = 23456;
        ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
        System.out.println("Server started!");
        Socket socket = server.accept();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output  = new DataOutputStream(socket.getOutputStream());


        String readUTFInput = input.readUTF();
        String serverResponse = "A record # N was sent!";
        System.out.println("Received: " + readUTFInput);

        System.out.print("Sent: " + serverResponse);
        output.writeUTF(serverResponse);

        databaseController.processUserInput();


    }
}
