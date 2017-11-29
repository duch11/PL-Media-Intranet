package plmedia.intranet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConMan {
  private static String USERNAME = "PLintra-dev";
  private static String PASSWORD = "MoreMoneyToFaisal";
  private static String CONN_STRING = "jdbc:mysql://localhost/intranetdb";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
  }

  public static PreparedStatement prepStat(String SQL) throws SQLException {
    PreparedStatement stmt = getConnection().prepareStatement(
        SQL,
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY);
    return stmt;
  }

  public static CallableStatement callStat(String SQL) throws SQLException{
    CallableStatement stmt = getConnection().prepareCall(
        SQL,
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY);
    return stmt;
  }

  public static ResultSet regStat(String SQL) throws SQLException {
    Statement stmt = getConnection().createStatement(
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY);
      return stmt.executeQuery(SQL);
  }


  public static void processException(SQLException e){
    System.err.println("Error message: " + e.getMessage());
    System.err.println("Error code: " + e.getErrorCode());
    System.err.println("SQL state: " + e.getSQLState());
  }
}

