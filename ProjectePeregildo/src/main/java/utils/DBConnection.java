package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection con;

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
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public void disconnect() {
        try {
            if(con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
