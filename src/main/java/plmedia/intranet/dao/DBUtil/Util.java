package plmedia.intranet.dao.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;

/**
 * A repository for different database utilities. Takes logic from util class in util package.
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */
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

  public int addChildrenToParent(Parent parent, ArrayList<Child> childList) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_ADD_CHILD_TO_PARENT)
    ) {
      for (Child c : childList) {
          stmt.setInt(1, parent.getUserId());
          stmt.setInt(2, c.getChildId());
          stmt.executeUpdate();

      }
      System.out.println("Success!");
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int deleteChildrenFromParent(Parent parent, ArrayList<Child> childList) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_DELETE_CHILD_FROM_PARENT)
    ) {
      for (Child c: childList) {
        stmt.setInt(1, parent.getUserId());
        stmt.setInt(2, c.getChildId());
        stmt.executeUpdate();
      }
      System.out.println("Success!");
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

}
