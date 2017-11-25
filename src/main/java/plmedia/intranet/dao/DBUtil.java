package plmedia.intranet.dao;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Parent;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBUtil extends JdbcUserDetailsManager.
 * Adds custom SQL statements and methods using those statements.
 * @author Tobias Thomsen
 */

public class DBUtil extends JdbcUserDetailsManager {

  // SQL's
  public static final String DEF_GET_ALL_PARENTS_SQL = "SELECT * FROM user WHERE type=\"par\"";
  public static final String DEF_GET_PERMISSIONS_BY_ID_SQL = "SELECT fk_permission_id FROM user_permission WHERE fk_user_id = ?";
  public static final String DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL = "{CALL GetChildrenByParentID(?)}";
  public static final String DEF_GET_CHILD_BY_ID_SQL = "SELECT * FROM child WHERE child_id = ?";

  // Fields
  private String getAllParentsSql = DEF_GET_ALL_PARENTS_SQL;
  private String getPermissionsByIdSql = DEF_GET_PERMISSIONS_BY_ID_SQL;
  private String getChildrenIDByParentIdSql = DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL;
  private String getChildByIDSql = DEF_GET_CHILD_BY_ID_SQL;

  // Methods
  // "Rent" SQL kald
  /**
   * Returns all users with parent type as Parent objects
   * with permissions and children as Strings, in an ArrayList.
   * @return
   */
  public ArrayList<Parent> getAllParents() {
    try(
      Connection conn = ConMan.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(getAllParentsSql);
    ) {
      ArrayList<Parent> parents = new ArrayList<>();
      ArrayList<String> permissions;
      ArrayList<Integer> children;

      while(rs.next()){
        permissions = getPermissions(rs.getInt("user_id"));
        children = GetChildrenIDByParentID(rs.getInt("user_id"));
        Parent parent = new Parent(
          rs.getInt("user_id"),
          rs.getString("password"),
          rs.getString("user_email"),
          rs.getString("first_name"),
          rs.getString("last_name"),
          permissions);
          parent.addChildren(children);
        parents.add(parent);
      }

      return parents;

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }

  // Method for prepared statements
  /**
   * Returns all permissions belonging to a user id as an ArrayList of Strings.
   * @param id
   * @return
   */
  public ArrayList<String> getPermissions(int id){
    try(
      Connection conn = ConMan.getConnection();
      PreparedStatement stmt = conn.prepareStatement(
        getPermissionsByIdSql,
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

  // Method for CallableStatements / Stores procedures
  /**
   * Returns all the children of a Parent as an ArrayList of Strings
   * @param id
   * @return
   */
  public ArrayList<Integer> GetChildrenIDByParentID(int id){
    try(
      Connection conn = ConMan.getConnection();
      CallableStatement stmt = conn.prepareCall(
        getChildrenIDByParentIdSql,
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

  /**
   * Returns child object by child id.
   * @param id
   * @return
   */
  public Child getChildObject(int id) {
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            getChildByIDSql,
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
