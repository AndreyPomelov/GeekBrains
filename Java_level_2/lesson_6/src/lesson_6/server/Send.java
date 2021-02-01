package lesson_6.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Send implements Runnable{

    private static Scanner in;
    private static PrintWriter out;
    public static Socket socket;

    @Override
    public void run() {
        try {
            in = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String message = in.nextLine();
            out.println(message);
        }

    }
}
