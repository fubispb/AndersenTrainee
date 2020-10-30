package chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private static final String HOST = "localhost";
    private static int clients_count = 0;
    private String clientName;


    public ClientHandler(Socket clientSocket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.outMessage = new PrintWriter(clientSocket.getOutputStream());
            this.inMessage = new Scanner(clientSocket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (inMessage.hasNext()) {
                clientName = inMessage.nextLine();
                break;
            }
        }
        System.out.println(SystemMessages.enteredUser(clientName));
        System.out.println(SystemMessages.countOfUsers(clients_count));
        server.privateMessage(this, SystemMessages.enteredUser(clientName));
        while (true) {
            if (inMessage.hasNext()) {
                String clientMessage = inMessage.nextLine();
                if (clientMessage.equalsIgnoreCase("/end")) {
                    server.privateMessage(this, "/stop");
                    this.close();
                    break;
                }
                System.out.println(clientName + ": " + clientMessage);
                String serverAnswer = "Server: very interesting... Go on!";
                server.privateMessage(this, serverAnswer);
                System.out.println(serverAnswer);
            }
        }
    }

    public void sendMessage(String message) {
        outMessage.println(message);
        outMessage.flush();
    }

    private void close() {
        server.removeClient(this);
        clients_count--;
        server.broadcastMessage(SystemMessages.userLeave(clientName, clients_count));
        System.out.println(SystemMessages.userLeave(clientName, clients_count));
    }

}
