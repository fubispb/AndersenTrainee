package chat.client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client(4433);
        client.start();
    }
}
