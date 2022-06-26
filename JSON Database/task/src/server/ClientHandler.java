package server;

import com.google.gson.Gson;
import server.cli.CommandExecutor;
import server.cli.commands.DeleteCommand;
import server.cli.commands.GetCommand;
import server.cli.commands.SetCommand;
import server.cli.requests.Request;
import server.cli.requests.Response;
import server.exceptions.BadRequest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler extends Thread {
    final Socket socket;
    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    private ServerSocket serverSocket;
    private boolean flag = false;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, ServerSocket server) {
        this.socket = s;
        this.inputStream = dis;
        this.outputStream = dos;
        this.serverSocket = server;
    }

    @Override
    public void run() {

        final CommandExecutor executor = new CommandExecutor();

        while (!flag) {
            try {
                Request request = new Gson().fromJson(inputStream.readUTF(), Request.class);
                Response response = new Response();

                try {
                    switch (request.getType()) {
                        case "get" -> {
                            GetCommand getCommand = new GetCommand(request.getKey());
                            executor.executeCommand(getCommand);
                            response.setValue(getCommand.getResult().toString());
                        }
                        case "set" -> {
                            SetCommand setCmd = new SetCommand(request.getKey(), request.getValue());
                            executor.executeCommand(setCmd);
                        }
                        case "delete" -> {
                            DeleteCommand deleteCmd = new DeleteCommand(request.getKey());
                            executor.executeCommand(deleteCmd);
                        }
                        case "exit" -> {
                            response.setResponse(Response.STATUS_OK);
                            outputStream.writeUTF(response.toJSON());
                            serverSocket.close();
                            return;
                        }
                        default -> throw new BadRequest();
                    }
                    response.setResponse(Response.STATUS_OK);
                } catch (Exception e) {
                    response.setResponse(Response.STATUS_ERROR);
                    response.setReason(e.getMessage());
                } finally {
                    outputStream.writeUTF(response.toJSON());
                    flag = true;
                }

            } catch (Exception e) {
                try {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                e.printStackTrace();
            }
        }
    }
}
