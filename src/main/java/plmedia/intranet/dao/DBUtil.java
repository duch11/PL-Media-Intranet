package plmedia.intranet.dao;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Parent;

import java.sql.*;
import java.util.ArrayList;
import plmedia.intranet.model.User;

/**
 * DBUtil extends JdbcUserDetailsManager.
 * Adds custom SQL statements and methods using those statements.
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

public class DBUtil {

  // "Rent" SQL kald
  /**
   * Returns all users with parent type as Parent objects
   * with permissions and children as Strings, in an ArrayList.
   * @return
   */

  public ArrayList<Parent> getAllParents() {
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(Statements.DEF_GET_ALL_USERS_BY_TYPE_SQL);

    ) {
      stmt.setString(1, "ROLE_PAR");
      ResultSet rs = stmt.executeQuery();

      ArrayList<Parent> parents = new ArrayList<>();
      ArrayList<String> permissions;


      while(rs.next()){
        permissions = getPermissions(rs.getInt("user_id"));

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

  /**
   * Returns child object by child id.
   * @param id
   * @return
   */
  public Child getChildObject(int id) {
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


  public int createParent(String password, String user_email, String first_name, String last_name, String type) {
    try(
        Connection conn = ConMan.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
            Statements.DEF_CREATE_PARENT_USER_SQL,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
    ) {

      if (CheckEmail(user_email) != 10){

        stmt.setString(1, password);
        stmt.setString(2, user_email);
        stmt.setString(3, first_name);
        stmt.setString(4, last_name);
        stmt.setString(5, type);
        stmt.setInt(6, 1);

        stmt.executeUpdate();
        System.out.println("success");

        return 1; // Error codes?

      }
        System.out.println("Fejl");
    } catch (SQLException e){
      e.printStackTrace();
    }

    return -1; // Error codes?

  }

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
