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
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;
import plmedia.intranet.model.Wing;

public class DBread {

  // Parents

  public Parent readParentByID(int id) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_PARENT_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Permission> permissions = readPermissionsByUserID((rs.getInt("user_id")));

      return new Parent(
        rs.getInt("user_id"),
        rs.getString("password"),
        rs.getString("user_email"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        permissions);


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public ArrayList<Parent> readAllParents() {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_ALL_USERS_BY_TYPE_SQL);
    ) {
      stmt.setString(1, "ROLE_PAR");
      ResultSet rs = stmt.executeQuery();

      ArrayList<Parent> parents = new ArrayList<>();

      while (rs.next()) {
        ArrayList<Permission> permissions = readPermissionsByUserID((rs.getInt("user_id")));
        parents.add(new Parent(
            rs.getInt("user_id"),
            rs.getString("password"),
            rs.getString("user_email"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            permissions));
      }

      return parents;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // Error code?
  }

  // Employees

  public Employee readEmployeeByID(int id) throws SQLException{
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_EMPLOYEE_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Permission> permissions = readPermissionsByUserID((rs.getInt("user_id")));

      return new Employee(
          rs.getInt("user_id"),
          rs.getString("password"),
          rs.getString("user_email"),
          rs.getString("first_name"),
          rs.getString("last_name"),
          permissions);
    }
  }

  public ArrayList<Employee> readAllEmployees()  {
      try (
          PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_EMPLOYEE_BY_ID_SQL);
      ) {
        ResultSet rs = stmt.executeQuery();
        ArrayList<Employee> employees = new ArrayList<>();
        while (rs.next()) {
          ArrayList<Permission> permissions = readPermissionsByUserID((rs.getInt("user_id")));

        }

      } catch (SQLException e) {
        e.printStackTrace();
      }
      return null;
    }

  // Children

  public ArrayList<Integer> readChildrenIDByParentID(int id) {
    try (
        CallableStatement stmt = ConMan.callStat(Statements.DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL)
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      ArrayList<Integer> children = new ArrayList<>();
      while (rs.next()) {
        children.add(rs.getInt("fk_child_id"));
      }
      return children;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // Error code?
  }

  public Child readChild(int id) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_CHILD_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      return new Child(
          rs.getInt("child_id"),
          rs.getString("first_name"),
          rs.getString("last_name"),
          rs.getDate("birthday"),
          rs.getString("address"),
          rs.getInt("fk_wing_id"),
          null,
          null,
          null,
          null);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // Error code?
  }

  // Permissions

  public Permission readPermissionByID(int id) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_PERMISSIONS_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();

      return new Permission(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3));

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Permission> readAllPermissions() {
    try (
        ResultSet rs = ConMan.regStat(Statements.DEF_GET_ALL_PERMISSIONS_SQL);
    ) {
      ArrayList<Permission> permissions = new ArrayList<>();
      while (rs.next()) {

        permissions.add(readPermissionByID(rs.getInt(1)));

      }
      return permissions;

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  public ArrayList<Permission> readPermissionsByUserID(int id) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_PERMISSION_ID_BY_USER_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Permission> permissions = new ArrayList<>();

      while (rs.next()) {
        permissions.add(readPermissionByID(rs.getInt("fk_permission_id")));
      }

      return permissions;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // Error code?
  }

  // Wing

  public Wing readWingByID(int id) throws SQLException {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_WING_BY_ID);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      return new Wing(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3));
    }

  }

  public ArrayList<Wing> readAllWings() throws SQLException {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_ALL_WINGS);
    ) {
      ResultSet rs = stmt.executeQuery();

      ArrayList<Wing> wings = new ArrayList<>();

      while (rs.next()) {

        wings.add(new Wing(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3)));

      }
      return wings;
      }
  }

  // Group

  public Group readGroupByID(int id) throws SQLException {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_GROUP_BY_ID);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      return new Group(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3));
    }

  }

  public ArrayList<Group> readAllGroups(int id) throws SQLException {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_ALL_GROUPS);
    ) {
      ResultSet rs = stmt.executeQuery();
      ArrayList<Group> groups = new ArrayList<>();
      while (rs.next()){
        groups.add(new Group(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3)));
      }
      return groups;
    }

  }


}