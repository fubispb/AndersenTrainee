package internet_shop.application;

import internet_shop.products.food.Apple;
import internet_shop.products.food.Coffee;
import internet_shop.products.food.Cookies;
import internet_shop.products.milk.Parmalat;
import internet_shop.products.not_food.Chair;
import internet_shop.products.not_food.Computer;
import internet_shop.products.not_food.Table;
import internet_shop.products.Product;
import internet_shop.warehouse.Warehouse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ToString
public class CommandHandler implements Serializable {

    private Warehouse warehouse;
    private final String clientName;
    private final Map<Product, Integer> bucket;
    private double currentBucketAmount;
    User user;

    public CommandHandler(User user) {
        this.user = user;
        warehouse = new Warehouse();
        this.clientName = user.getName();
        bucket = new HashMap<>();
    }

    public String getClientName() {
        return clientName;
    }

    public int getBucketSize() {
        return bucket.size();
    }

    public double getCurrentBucketAmount() {
        return currentBucketAmount;
    }

    public void userCommandHandler(String userInput) {
        String[] splittedInputs = userInput.split(" ");
        try {
            Commands command = Commands.valueOf(splittedInputs[0].toUpperCase());
            if (verifyUserInput(splittedInputs)) {
                switch (command) {
                    case EXIT:
                        exitSystem();
                    case ADD:
                        if (bucket.containsKey(stringToProduct(splittedInputs[1]))) {
                            addIfExists(stringToProduct(splittedInputs[1]), Integer.parseInt(splittedInputs[2]));
                            break;
                        }
                        add(stringToProduct(splittedInputs[1]), Integer.parseInt(splittedInputs[2]));
                        break;
                    case CLEAR:
                        clearBucket();
                        break;
                    case SHOWALL:
                        showProductsList();
                        break;
                    case DELETE:
                        if (!bucket.containsKey(stringToProduct(splittedInputs[1]))) {
                            System.out.println(SystemMessagesAndCommands.incorrectInput("Your bucket doesn't contains " +
                                    splittedInputs[1]));
                        } else deleteProduct(stringToProduct(splittedInputs[1]));
                        break;
                    case SHOWBUCKET:
                        showProductsInBucket();
                        break;
                    case HELP:
                        System.out.println(SystemMessagesAndCommands.startProgramMessage);
                        break;
                    case CONFIRM:
                        try {
                            System.out.println("Your order is confirmed. Order id: " +
                                    ConnectBaseService.confirmOrderAndGetId(bucket, user, currentBucketAmount));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        clearBucket();
                        break;
                    case INFO:
                        ConnectBaseService.userInfo(user);
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown command: " + splittedInputs[0]);
        }
    }

    private void showProductsList() {
        System.out.println("You can choose these products:");
        System.out.println(Product.listOfProducts);
    }

    private void add(Product product, int count) {
        bucket.put(product, count);
        incrementAmount(product, count);
        showBucketContainsAndAmount();
        System.out.println(SystemMessagesAndCommands.currentBucketAmount(currentBucketAmount));
    }

    private void addIfExists(Product product, int count) {
        int currentCount = bucket.get(product);
        bucket.put(product, currentCount + count);
        incrementAmount(product, count);
        showBucketContainsAndAmount();
        System.out.println(SystemMessagesAndCommands.currentBucketAmount(currentBucketAmount));
    }

    private void showProductsInBucket() {
        if (bucket.size() == 0) {
            System.out.println(SystemMessagesAndCommands.emptyBucket);
            return;
        }
        System.out.println(bucket);
    }

    private void deleteProduct(Product product) {
        decrementAmount(product);
        bucket.remove(product);
        showBucketContainsAndAmount();
        System.out.println(SystemMessagesAndCommands.currentBucketAmount(currentBucketAmount));
    }

    private void clearBucket() {
        bucket.clear();
        currentBucketAmount = 0;
    }

    private void exitSystem() {
        System.out.println("Exiting system...");
        try {
            FileOutputStream outputStream = new FileOutputStream(clientName + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            ConnectBaseService.disconnect();
        } catch (IOException e) {
            log.error("Start log. " + e);
        }
        System.exit(0);
    }

    private void showBucketContainsAndAmount() {
        System.out.println(SystemMessagesAndCommands.containsOfBucket + "\n" + bucket);
    }

    private void incrementAmount(Product product, int count) {
        currentBucketAmount += (product.getPrice() * count);
    }

    private void decrementAmount(Product product) {
        int currentValue = bucket.get(product);
        currentBucketAmount -= (currentValue * product.getPrice());
    }

    private boolean verifyUserInput(String[] userInput) {
        if (Objects.isNull(userInput)) {
            System.out.println(SystemMessagesAndCommands.incorrectInput(null));
            return false;
        }
        if (userInput.length == 0) {
            System.out.println(SystemMessagesAndCommands.incorrectInput(null));
            return false;
        }
        if (userInput.length == 1) {
            for (String str : SystemMessagesAndCommands.listOfSingleCommands) {
                if (userInput[0].equalsIgnoreCase(str)) return true;
            }
            System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[0]));
            return false;
        }
        if (userInput.length == 2) {
            if (userInput[0].equalsIgnoreCase(SystemMessagesAndCommands.deleteCommand)) {
                for (String str : Product.listOfProducts) {
                    if (userInput[1].equalsIgnoreCase(str)) return true;
                }
                System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[1]));
                return false;
            }
            System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[0]));
            return false;
        }
        if (userInput.length == 3) {
            if (userInput[0].equalsIgnoreCase(SystemMessagesAndCommands.addCommand)) {
                for (String str : Product.listOfProducts) {
                    if (userInput[1].equalsIgnoreCase(str)) {
                        try {
                            int value = Integer.parseInt(userInput[2]);
                            if (value <= 0) {
                                System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[2]));
                                return false;
                            }
                            return true;
                        } catch (NumberFormatException e) {
                            System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[2]));
                            return false;
                        }
                    }
                }
                System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[1]));
                return false;
            }
            System.out.println(SystemMessagesAndCommands.incorrectInput(userInput[0]));
        }
        return false;
    }

    private Product stringToProduct(String str) {
        Products product = Products.valueOf(str.toUpperCase());
        switch (product) {
            case APPLE:
                return new Apple();
            case COFFEE:
                return new Coffee();
            case COOKIES:
                return new Cookies();
            case CHAIR:
                return new Chair();
            case COMPUTER:
                return new Computer();
            case TABLE:
                return new Table();
            case PARMALAT:
                return new Parmalat();
        }
        return null;
    }

    enum Products {
        APPLE, COFFEE, COOKIES, CHAIR, COMPUTER, TABLE, PARMALAT
    }

    enum Commands {
        EXIT, CLEAR, SHOWALL, SHOWBUCKET, HELP, ADD, DELETE, CONFIRM, INFO
    }


}
