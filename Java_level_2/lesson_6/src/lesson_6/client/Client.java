package lesson_6.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            Receive.socket = socket;
            Send.socket = socket;

            lesson_6.client.Receive receive = new lesson_6.client.Receive();
            Thread threadReceive = new Thread(receive);
            threadReceive.start();

            lesson_6.client.Send send = new lesson_6.client.Send();
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
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
