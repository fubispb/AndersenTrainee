package chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int port;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private BufferedReader reader;
    private String message;

    public Client(int port) {
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket("localhost", port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));
            setName();
            while (true) {
                try {
                    readServerMessages();
                    System.out.print("Enter your message here: ");
                    message = reader.readLine();
                    out.println(message);
                    out.flush();
                    if (message.equalsIgnoreCase("/end")) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setName() {
        System.out.println("Please enter your name: ");
        try {
            message = reader.readLine();
            out.println(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readServerMessages() {
        if (in.hasNext()) {
            System.out.println(in.nextLine());
        }
    }
}
