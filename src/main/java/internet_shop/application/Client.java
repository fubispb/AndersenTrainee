package internet_shop.application;

import java.util.HashMap;
import java.util.Scanner;

public class Client {

    private CommandHandler commands;
    private Scanner in = new Scanner(System.in);

    public void start() {
        System.out.println("Please enter your name:");
        String userInput = in.nextLine();
        commands = new CommandHandler(userInput);
        System.out.println("Welcome in our internet shop, " + userInput + "!");
        System.out.println(SystemMessagesAndCommands.startProgramMessage);
        while (true) {
            System.out.println("Enter command: ");
            userInput = in.nextLine();
            commands.userCommandHandler(userInput);
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }



}
