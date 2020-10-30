package chat.server;

import chat.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class Server {
    private final int port;
    private final ArrayList<ClientHandler> clients = new ArrayList<>();
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public Server() {
        this.port = 4433;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Server is stopped");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMessage(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void privateMessage(ClientHandler client, String message) {
        for (ClientHandler c : clients) {
            if (Objects.equals(client, c)) {
                c.sendMessage(message);
                break;
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}
