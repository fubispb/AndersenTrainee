package internet_shop.application;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

@Slf4j
public class Client {

    private CommandHandler commands;
    private Scanner in = new Scanner(System.in);
    private User user;

    public void start() {
        try {
            ConnectBaseService.connect();
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Start log. " + e);
        }
        System.out.println("Please enter your name:");
        String userInput = in.nextLine();
        user = new User(ConnectBaseService.getUserIdByName(userInput), userInput);
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
        else commands = new CommandHandler(user);
        System.out.println(user);
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
