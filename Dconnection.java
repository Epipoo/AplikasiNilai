package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dconnection {
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/penilaian";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw e;
        }
    }
}
