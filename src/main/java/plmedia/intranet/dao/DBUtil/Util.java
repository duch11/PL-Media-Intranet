package plmedia.intranet.dao.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;

public class Util {


  public int CheckEmail(String email) {
    try(
        Connection conn = ConMan.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(Statements.DEF_GET_ALL_EMAIL);
    ){
      while(rs.next()) {
        if (rs.getString("user_email").equals(email)){
          return 10;
        }
      }
    } catch (SQLException e){
      e.printStackTrace();
    }
    return -1; // Error code?
  }
}
