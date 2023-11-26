package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConnection class manages the connection to the database.
 */
public class DBConnection {
    private Connection con;

    /**
     * Establishes a connection to the database if not already connected.
     *
     * @return The database connection.
     */
    public Connection connect() {
        try {
            if (con == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = DBConstants.CONNECTION;
                String user = System.getenv("DB_USERNAME");
                String pass = System.getenv("DB_PASSWORD");
                con = DriverManager.getConnection(url, user, pass);
            }
        } catch (ClassNotFoundException e) {
            handleException(e, "Driver class not found");
        } catch (SQLException e) {
            handleException(e, "Error establishing database connection");
        }
        return con;
    }

    /**
     * Disconnects from the database if connected.
     */
    public void disconnect() {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            handleException(e, "Error closing database connection");
        }
    }

    /**
     * Handles an exception by printing the stack trace. In a real application, consider logging or handling
     * the exception more appropriately based on your use case.
     *
     * @param e      The exception to handle.
     * @param errMsg Additional error message.
     */
    private void handleException(Exception e, String errMsg) {
        System.err.println(errMsg);
        e.printStackTrace();
    }
}
