package plmedia.intranet.dao;

import java.sql.Connection;

/**
 * ConnectionManager is a class to control, open, and close JDBC
 * connections to and from the MySQL database more persistently.
 * @author Simon le Févre Ryom
 */

public class ConnectionManager {

  private static ConnectionManager instance = null;

  // Defining the login for the database via application properties.
  //DO.
  private String dbUserName;
  private String dbUserPass;

  // Defining the database type as a MySQL database from the enum.
  private DBType dbtybe = DBType.MYSQL;

  private Connection conn = null;

  public ConnectionManager() {
  }

  // initiates
  public static ConnectionManager getInstance() {
    if(instance == null){
      instance = new ConnectionManager();
    }
    return instance;
  }

  // A setter for DBType.
  public void setDbtybe(DBType dbtybe) {
    this.dbtybe = dbtybe;
  }



}
