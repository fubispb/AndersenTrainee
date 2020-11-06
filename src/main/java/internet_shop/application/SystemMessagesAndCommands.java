package internet_shop.application;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SystemMessagesAndCommands {

    public final static List<String> listOfSingleCommands = Arrays.asList("/exit", "/clear", "/showall", "/showbucket", "/help");

    public final static String addCommand = "/add";
    public final static String deleteCommand = "/delete";
    public final static String emptyBucket = "Your bucket is empty.";
    public final static String startProgramMessage = "Allowed commands are:\n" +
            "/showall - for showing all products. \n" +
            "/add productname count - for add a product. \n" +
            "/showbucket - for showing current products in your bucket. \n" +
            "/delete productname - for deleting product from bucket. \n" +
            "/clear - for clear your bucket. \n" +
            "/exit - for exit programm. \n" +
            "/help - fot repeat start message.";

    public static String incorrectInput(String input) {
        if (Objects.nonNull(input)) return "Incorrect input: " + input + ". For help type /help.";
        return "Please enter not null command.";
    }

    public static String currentBucketAmount(int amount) {
        return "Current bucket amount is: " + amount;
    }

}
