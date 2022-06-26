package server;

import server.database.Database;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
        System.out.println("Server started!");
        Database.INSTANCE.init();

        while (!server.isClosed()) {
            Socket socket;

            try {
                socket = server.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                Thread thread = new ClientHandler(socket, dataInputStream, dataOutputStream, server);
                thread.start();
            } catch (Exception e) {
                server.close();
            }
        }
    }
}
