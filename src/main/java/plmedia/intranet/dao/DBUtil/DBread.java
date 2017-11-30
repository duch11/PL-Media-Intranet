package plmedia.intranet.dao.DBUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.dao.repository.PermissionRepo;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;

public class DBread {

  PermissionRepo permissionRepo = new PermissionRepo();

  public ArrayList<Parent> readAllParents() {
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_ALL_USERS_BY_TYPE_SQL);
    ) {
      stmt.setString(1, "ROLE_PAR");
      ResultSet rs = stmt.executeQuery();

      ArrayList<Parent> parents = new ArrayList<>();
      ArrayList<Permission> permissions;

      while(rs.next()){
        permissions = permissionRepo.readPermissionsByUserID((rs.getInt("user_id")));

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

  public ArrayList<Integer> readChildrenIDByParentID(int id){
    try(
      CallableStatement stmt = ConMan.callStat(Statements.DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL)
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
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_CHILD_BY_ID_SQL);
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
