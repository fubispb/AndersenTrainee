package chat.server;

public class SystemMessages {

    public static String countOfUsers(int count) {
        return "There are " + count + " user(s) in chat at now.";
    }

    public static String enteredUser(String name) {
        return name + " enter the chat.";
    }

    public static String userLeave(String name, int count) {
        return "User " + name + " leave the chat. " + count + " users remaining";
    }

}
