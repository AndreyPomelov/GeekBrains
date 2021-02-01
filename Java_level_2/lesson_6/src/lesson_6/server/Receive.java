package lesson_6.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Receive implements Runnable{

    private static Scanner in;
    private static PrintWriter out;
    public static Socket socket;

    @Override
    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String str = in.nextLine();

            if (str.equals("/end")) {
                System.out.println("Клиент вышел из чата");
                break;
            }
            System.out.println(str);
        }
    }
}
