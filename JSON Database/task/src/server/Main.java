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
        databaseController.processUserInput();


        Socket socket = getSocket();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output  = new DataOutputStream(socket.getOutputStream());
        readAndWrite(input, output);

        databaseController.processUserInput();


    }

    private static void readAndWrite(DataInputStream input, DataOutputStream output) throws IOException {
        System.out.println("Received: " + input.readUTF());
        System.out.print("Sent: " + "A record # N was sent!");
        output.writeUTF("A record # N was sent!");

    }

    private static Socket getSocket() throws IOException {
        String address = "127.0.0.1";
        int port = 23456;
        ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
        System.out.println("Server started!");
        Socket socket = server.accept();
        return socket;
    }
}
