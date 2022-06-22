package client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {



        Socket socket = getSocket();
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());


        System.out.println("Client started!");
        System.out.print("Sent: " + "Give me a record of # N");
        output.writeUTF("Give me a record of # N");
        System.out.println("Received: " + input.readUTF());
    }

    private static Socket getSocket() throws IOException {
        String address = "127.0.0.1";
        int port = 23456;
        Socket socket = getSocket(address, port);
        System.out.println("Client started!");
        return socket;
    }

    private static Socket getSocket(String address, int port) throws IOException {
        return new Socket(InetAddress.getByName(address), port);

    }

}
