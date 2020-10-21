package patterns.mediator;

import java.util.ArrayList;
import java.util.List;

public class MediatorPattern {

    public static void main(String[] args) {

        TextChat chat = new TextChat();

        User admin = new Admin(chat, "Ivan Ivanovich");
        User u1 = new SimpleUser(chat, "Ivan");
        User u2 = new SimpleUser(chat, "Vova");
        User u3 = new SimpleUser(chat, "Alex");
        u2.setEnable(false);

        chat.setAdmin(admin);
        chat.addUser(u1);
        chat.addUser(u2);
        chat.addUser(u3);

        admin.sendMessage("Hello");
    }
}

abstract class User {
    protected Chat chat;
    protected String name;
    protected boolean isEnable = true;

    abstract void getMessage(String message);

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }
}

class Admin extends User {

    public Admin(Chat chat, String name) {
        super(chat, name);
    }

    public void getMessage(String message) {
        System.out.println("Администратор " + getName() + " получает сообщение '" + message + "'");
    }
}

class SimpleUser extends User {
    public SimpleUser(Chat chat, String name) {
        super(chat, name);
    }

    public void getMessage(String message) {
        System.out.println("Пользователь " + getName() + " получает сообщение '" + message + "'");
    }
}

interface Chat {
    void sendMessage(String message, User user);
}

class TextChat implements Chat {
    private User admin;
    private final List<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        if (admin != null && admin instanceof Admin) {
            this.admin = admin;
        } else {
            throw new RuntimeException("Not enough rights.");
        }
    }

    public void addUser(User u) {
        if (admin == null) {
            throw new RuntimeException("There is no Admin user in chat!");
        }

        if (u instanceof SimpleUser) {
            users.add(u);
        } else {
            throw new RuntimeException("You can't enter this chat by Admin login.");
        }
    }

    public void sendMessage(String message, User user) {
        if (user instanceof Admin) {
            for (User u : users) {
                u.getMessage(user.getName() + ": " + message);
            }
        }
        if (user instanceof SimpleUser) {
            for (User u : users) {
                if (u != user && u.isEnable())
                    u.getMessage(user.getName() + ": " + message);
            }
            if (admin.isEnable())
                admin.getMessage(user.getName() + ": " + message);
        }
    }

}