package plmedia.intranet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMan {
    private static String USERNAME = "root";
    private static String PASSWORD = "";
    private static String CONN_STRING = "jdbc:mysql://localhost/intranetdb";

    public static Connection getConnection() throws SQLException {
                return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    public static void processException(SQLException e){
        System.err.println("Error message: " + e.getMessage());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
    }
}
