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

        String toSendToServer = getToSendToServer();
        sendMessageToServer(output, toSendToServer);
        received(input);
    }

    private static void sendMessageToServer(DataOutputStream output, String toSendToServer) throws IOException {
        output.writeUTF(toSendToServer);
    }

    private static String getToSendToServer() {
        String toSendToServer = "Give me a record of # N";
        System.out.println("Client started!");
        System.out.print("Sent: " + toSendToServer);
        return toSendToServer;
    }

    private static void received(DataInputStream input) throws IOException {
        System.out.println("Received: " + input.readUTF());
    }

    private static Socket getSocket() throws IOException {
        String address = "127.0.0.1";
        int port = 23456;
        Socket socket = new Socket(InetAddress.getByName(address), port);
        System.out.println("Client started!");
        return socket;
    }

}
