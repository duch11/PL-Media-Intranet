package plmedia.intranet.dao.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;

public class Util {

  public int checkEmail(String email) {
    try(
        ResultSet rs = ConMan.regStat(Statements.DEF_GET_ALL_EMAIL);
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
