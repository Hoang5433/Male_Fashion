package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mySQL://localhost:3306/male_fashion";
            String username = "root";
            String password = "";
            c = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không tìm thấy driver MySQL", e);
        }
        return c;
    }
}
