package plmedia.intranet.dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;

/**
 * A repository for different database utilities. Takes logic from util class in util package.
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */
public class Util {

  @Autowired
  BCryptPasswordEncoder encoder;


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

  public int checkPassword(int id, String password) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_GET_PASSWORD_BY_USER_ID);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();
      String userDBPass = rs.getString("password");

      if(encoder.matches(password, userDBPass)) {
        return 1;
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public String readHashedPassByUserID(int id) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_GET_PASSWORD_BY_USER_ID);
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();
      String pass = rs.getString("password");
      return pass;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}
