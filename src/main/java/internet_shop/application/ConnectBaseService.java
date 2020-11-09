package internet_shop.application;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class ConnectBaseService {

    private static Connection connection;
    private static Statement statement;


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/my_shop", "admin", "123456");
        statement = connection.createStatement();
        System.out.println("Connection established.");
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Start log. " + e);
        }
    }



    public static String infoGetByUserID() {


        return "";
    }

}
