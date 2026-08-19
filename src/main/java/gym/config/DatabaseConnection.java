package gym.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/gym_db";
    private static final String USER = "postgres";
    private static final String PASS = "password"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL JDBC Driver not found!");
            throw new SQLException(e);
        }
    }
}


