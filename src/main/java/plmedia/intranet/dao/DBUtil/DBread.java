package plmedia.intranet.dao.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Parent;

public class DBread {

  public ArrayList<Parent> readAllParents() {
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(Statements.DEF_GET_ALL_USERS_BY_TYPE_SQL);

    ) {
      stmt.setString(1, "ROLE_PAR");
      ResultSet rs = stmt.executeQuery();

      ArrayList<Parent> parents = new ArrayList<>();
      ArrayList<String> permissions;

      while(rs.next()){
        permissions = readPermissions(rs.getInt("user_id"));

        Parent parent = new Parent(
            rs.getInt("user_id"),
            rs.getString("password"),
            rs.getString("user_email"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            permissions);
        parents.add(parent);
      }

      return parents;

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }

  public ArrayList<String> readPermissions(int id){
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            Statements.DEF_GET_PERMISSIONS_BY_ID_SQL,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      ArrayList<String> permissions = new ArrayList<>();

      while (rs.next()) {
        permissions.add(rs.getString("fk_permission_id"));
      }

      return permissions;

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }

  public ArrayList<Integer> readChildrenIDByParentID(int id){
    try(
        Connection conn = ConMan.getConnection();
        CallableStatement stmt = conn.prepareCall(
            Statements.DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Integer> children = new ArrayList<>();
      while (rs.next()) {
        children.add(rs.getInt("fk_child_id"));
      }
      return children;
    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }

  public Child readChildObject(int id) {
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            Statements.DEF_GET_CHILD_BY_ID_SQL,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      ArrayList<String> nap = new ArrayList<>();
      Child child = null;
      while(rs.next()){

        child = new Child(
            rs.getInt("child_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getDate("birthday"),
            rs.getString("address"),
            rs.getInt("fk_wing_id"),
            nap,
            null,
            null,
            null
        );
      }
      return child;

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }
}
