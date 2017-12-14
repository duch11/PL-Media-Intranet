package plmedia.intranet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */
public class ConMan {
  private static String USERNAME = "PLintra_prod";
  private static String PASSWORD = "MoreMoneyToFaisal";
  private static String CONN_STRING = "jdbc:mysql://intranetdb.cxek8jpy9hiy.eu-central-1.rds.amazonaws.com:3306/intranetdb";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
  }

  public static PreparedStatement prepStat(Connection con, String SQL) throws SQLException {
    PreparedStatement stmt = con.prepareStatement(
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

