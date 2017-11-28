package plmedia.intranet.dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Parent;


public class DBcreate {

  Util util = new Util();

  public int createParent(Parent parent) {
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            Statements.DEF_CREATE_PARENT_USER_SQL,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
    ) {

      if (util.CheckEmail(parent.getUserEmail()) != 10){
        stmt.setString(1, parent.getPassword());
        stmt.setString(2, parent.getUserEmail());
        stmt.setString(3, parent.getFirstName());
        stmt.setString(4, parent.getLastName());
        stmt.setString(5, "ROLE_PAR");
        stmt.setInt(6, 1);

        stmt.executeUpdate();
        System.out.println("Bruger oprettet");
        return 1; // Error codes?
      }
      System.out.println("Bruger ikke oprettet");
    } catch (SQLException e){
      e.printStackTrace();
    }
    return -1; // Error codes?
  }
}
