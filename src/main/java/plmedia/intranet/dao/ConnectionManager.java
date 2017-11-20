package plmedia.intranet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;

/**
 * ConnectionManager is a class to control, open, and close JDBC
 * connections to and from the MySQL database more persistently.
 * @author Simon le Févre Ryom
 */

public class ConnectionManager {

  // The ConnectionManager starts by setting an instance of itself as default null.
  private static ConnectionManager instance = null;

  //defining the login for the database via application.properties files.
  //NOT sure if working correctly!
  @Value("${spring.datasource.username}")
  private static String dbUserName;

  @Value("${spring.datasource.password}")
  private static String dbUserPass;

  @Value("${spring.datasource.url}")
  private static String CONN_STRING;

  // Defining the database type as a MySQL database .
  private String dbTybe = "MYSQL";

  // Setting the connection field to null as default. There can only be one to maintain this class as a singleton.
  private Connection conn = null;

  // Constructor for ConnectionManager is set to private so it can only be used inside this class.
  private ConnectionManager() {
  }

  // Checks to see if the one instance is null, if not, it will instantiate it and return either way.
  // This is so to get a reference to the one and only ConnectionManager object for the whole application,
  // other parts of the application will call the static method ConnectionManager.getInstance.
  public static ConnectionManager getInstance() {
    if(instance == null){
      instance = new ConnectionManager();
    }
    return instance;
  }

  // Parses the parameters set above to open a connection to the database.
  // Prints an error message if something goes wrong.
  private boolean openConnection() {
    try {
      conn = DriverManager.getConnection(CONN_STRING,dbUserName,dbUserPass);
      return true;
    }catch (SQLException e){
      System.err.println(e);
      return false;
    }
  }

  //This method is called from the only instance of ConnectionManager. It checks to see if the connection
  // is null.
  // If null, then it opens it and returns the reference.
  // If open, then it just returns the already open connection.
  public Connection getConnection() {
    if (conn == null) {
      if (openConnection()) {
        System.out.println("Connection opened");
        return conn;
      } else {
        return null;
      }
    }
    return conn;
  }

  // This method both closes the connection and sets it to null.
  public void close() {
    System.out.println("Closing connection");
    try {
      conn.close();
      conn = null;
    } catch (Exception e) {
    }
  }
}
