package lesson_6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Успешный старт сервера");

            socket = serverSocket.accept();
            Receive.socket = socket;
            Send.socket = socket;
            System.out.println("Клиент вошёл в чат");

            Receive receive = new Receive();
            Thread threadReceive = new Thread(receive);
            threadReceive.start();

            Send send = new Send();
            Thread threadSend = new Thread(send);
            threadSend.start();

            try {
                threadReceive.join();
                threadSend.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
