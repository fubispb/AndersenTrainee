package internet_shop.application;

import org.slf4j.Logger;
import java.io.*;
import java.util.Scanner;

public class Client {

    private CommandHandler commands;
    private Scanner in = new Scanner(System.in);
    private Logger log;

    public void start() {
        System.out.println("Please enter your name:");
        String userInput = in.nextLine();
        File file = new File(userInput + ".ser");
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(userInput + ".ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                commands = (CommandHandler) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                log.error("Start log. " + e);
            }
        }
        else commands = new CommandHandler(userInput);
        System.out.println("Welcome in our internet shop, " + userInput + "!");
        System.out.println(SystemMessagesAndCommands.startProgramMessage + "\n");
        if (commands.getBucketSize() != 0) {
            System.out.println(SystemMessagesAndCommands.containsOfBucket);
            commands.userCommandHandler("showbucket");
        }
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
