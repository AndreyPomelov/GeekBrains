package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Server server;
    private DataInputStream is;
    private DataOutputStream os;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void sendMessage(String message) throws IOException {
        os.writeUTF(message);
        os.flush();
    }

    @Override
    public void run() {
        try {
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connection is active");
            while (true) {
                String message = is.readUTF();
                System.out.println("Received: " + message);
                server.broadCast(message);
            }
        }
        catch (Exception e) {
            System.err.println("Connection was broken");
            server.kickClient(this);
        }
    }
}
