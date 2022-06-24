package server;

import server.Commands.BaseCommand;
import server.Commands.Command;
import server.Commands.CommandController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    SimulatedDB simulatedDB = new SimulatedDB();
    private String request;
    private String response;
    private CommandController commandController;

    public void init() {
        int PORT = 6991;
        String ADDRESS = "127.0.0.1";
        sendServerStartInfo();
        try (ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {

            while (true) {
                try (
                        Socket socket = server.accept();
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    try {
                        getClientMessage(input);
                        response = executeInputCommand();
                    } catch (RuntimeException e) {
                        response = new BaseCommand().printError();
                    } finally {
                        sendMessageToClient(output, response);
                        if ("exit".equals(commandController.getCommand().getType())) {
                            exit();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getClientMessage(DataInputStream inputStream) throws IOException {
        request = inputStream.readUTF();
        System.out.println("Received: " + request);
    }

    private void sendMessageToClient(DataOutputStream output, String response) throws IOException {
        output.writeUTF(response);
        System.out.println("Sent: " + response);
    }

    private String executeInputCommand() {
        commandController = new CommandController();
        commandController.createCommand(request);
        commandController.setDataBase(simulatedDB);
        return commandController.executeCommand();
    }

    private void sendServerStartInfo() {
        System.out.println("Server started!");
    }

    private void exit() {
        System.exit(0);
    }
}
