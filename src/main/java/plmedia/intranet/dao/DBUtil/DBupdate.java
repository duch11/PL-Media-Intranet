package plmedia.intranet.dao.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.dao.repository.UtilRepo;
import plmedia.intranet.model.Allergen;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;
import plmedia.intranet.model.Wing;

/**
 * a class for all the logic for updating objects and foreign connections.
 * @author Simon le Févre Ryom
 */
public class DBupdate {

  DBread dbr = new DBread();
  Util util = new Util();
  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


  // Model updaters
  public int updateParent(Parent parent) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_UPDATE_USER);
      String parentPass = parent.getPassword();

      if(!parent.getPassword().equals(null)) {
        String hashedPassword = passwordEncoder.encode(parentPass);
        stmt.setString(1, hashedPassword);
      } else {

      }


      stmt.setString(2, parent.getUserEmail());
      stmt.setString(3, parent.getFirstName());
      stmt.setString(4, parent.getLastName());
      stmt.setInt(5, parent.getUserId());

      stmt.executeUpdate();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public int updateEmployee(Employee employee) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_UPDATE_USER);
      String employeePass = employee.getPassword();

      if(!(employee.getPassword() == null)) {
        String hashedPassword = passwordEncoder.encode(employeePass);
        stmt.setString(1, hashedPassword);
      } else {
        stmt.setString(1, util.readHashedPassByUserID(employee.getUserId()));
      }
      stmt.setString(2, employee.getUserEmail());
      stmt.setString(3, employee.getFirstName());
      stmt.setString(4, employee.getLastName());
      stmt.setInt(5, employee.getUserId());

      stmt.executeUpdate();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public int updateChild(Child child) {
    try (
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_UPDATE_CHILD);
      stmt.setString(1, child.getFirstName());
      stmt.setString(2, child.getLastName());
      stmt.setDate(3, (Date) child.getBirthday());
      stmt.setString(4, child.getAddress());
      stmt.setInt(5, child.getWingId());

      stmt.executeUpdate();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int updateWing(Wing wing) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_UPDATE_WING);
      stmt.setString(1, wing.getWingName());
      stmt.setString(2, wing.getWingDescription());
      stmt.setInt(3, wing.getWingID());
      stmt.executeUpdate();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int updateGroup(Group group) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_UPDATE_GROUP);
      stmt.setString(1, group.getGroupName());
      stmt.setString(2, group.getGroupDescription());
      stmt.setInt(3, group.getId());

      stmt.executeUpdate();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  // FK updaters
  public int updateChildToParent(Parent parent, ArrayList<Integer> newChildren) {
    try (

        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement writeStmt = ConMan.prepStat(con, Statements.DEF_ADD_CHILD_TO_PARENT);
      PreparedStatement deleteStmt = ConMan.prepStat(con, Statements.DEF_DELETE_CHILD_FROM_PARENT);
      ArrayList<Integer> orgChildren = dbr.readChildrenIDByParentID(parent.getUserId());

      List<Integer> toWrite = new ArrayList<>(newChildren);
      List<Integer> toDelete = new ArrayList<>(orgChildren);

      toWrite.removeAll(orgChildren);
      toDelete.removeAll(newChildren);

      for (Integer i : toWrite) {
        writeStmt.setInt(1, parent.getUserId());
        writeStmt.setInt(2, toWrite.get(i));
        writeStmt.executeUpdate();
      }

      for (Integer i: toDelete) {
        deleteStmt.setInt(1,parent.getUserId());
        deleteStmt.setInt(2, toDelete.get(i));
        deleteStmt.executeUpdate();
      }
      System.out.println("");
    } catch (SQLException e){
      e.printStackTrace();
    }
    return -1;
  }

  public int updatePermissionByID(Employee employee, ArrayList<Integer> newPermission) {
    try (

        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement writeStmt = ConMan.prepStat(con, Statements.DEF_ADD_PERMISSION_TO_USER);
      PreparedStatement deleteStmt = ConMan.prepStat(con, Statements.DEF_DELETE_PERMISSION_FROM_USER);
      /**Get original permissions and get their ID values*/
      ArrayList<Permission> temp = dbr.readPermissionsByUserID(employee.getUserId());
      ArrayList<Integer> orgPermission = new ArrayList<>();

      for (Permission p: temp) {
        orgPermission.add(p.getPermissionID());
      }

      List<Integer> toWrite = new ArrayList<>(newPermission);
      List<Integer> toDelete = new ArrayList<>(orgPermission);
      toWrite.removeAll(orgPermission);
      toDelete.removeAll(newPermission);

      for (Integer i : toWrite) {
        writeStmt.setInt(1, employee.getUserId());
        writeStmt.setInt(2, i);
        writeStmt.executeUpdate();
      }

      for (Integer i: toDelete) {
        deleteStmt.setInt(1, employee.getUserId());
        deleteStmt.setInt(2, i);
        deleteStmt.executeUpdate();
      }

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int updateChildAllergens(Child child, ArrayList<Integer> newAllergen) {
    try(

        Connection con = ConMan.getConnection();
    ) {
      PreparedStatement writeStmt = ConMan.prepStat(con, Statements.DEF_ADD_ALLERGEN_TO_CHILD);
      PreparedStatement deleteStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALLERGEN_FROM_CHILD);
      ArrayList<Allergen> temp = dbr.readAllergenByChildID(child.getChildId());
      ArrayList<Integer> orgAllergen = new ArrayList<>();

      for (Allergen a: temp) {
        orgAllergen.add(a.getAllergenID());
      }

      List<Integer> toWrite = new ArrayList<>(newAllergen);
      List<Integer> toDelete = new ArrayList<>(orgAllergen);

      toWrite.removeAll(orgAllergen);
      toDelete.removeAll(newAllergen);

      for (Integer i : toWrite) {
        writeStmt.setInt(1, child.getChildId());
        writeStmt.setInt(2, toWrite.get(i));
        writeStmt.executeUpdate();
      }

      for (Integer i: toDelete) {
        deleteStmt.setInt(1, child.getChildId());
        deleteStmt.setInt(2, toDelete.get(i));
        deleteStmt.executeUpdate();
      }

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int updateEmployeeGroup(Employee employee, ArrayList<Integer> newGroup) {
    try(
        Connection con = ConMan.getConnection();

    ) {
      PreparedStatement writeStmt = ConMan.prepStat(con, Statements.DEF_ADD_GROUP_TO_EMPLOYEE);
      PreparedStatement deleteStmt = ConMan.prepStat(con, Statements.DEF_DELETE_GROUP_FROM_EMPLOYEE);
      ArrayList<Group> temp = dbr.readGroupIDsByUserID(employee.getUserId());
      ArrayList<Integer> orgGroup = new ArrayList<>();

      for (Group g: temp) {
        orgGroup.add(g.getId());
      }

      List<Integer> toWrite = new ArrayList<>(newGroup);
      List<Integer> toDelete = new ArrayList<>(orgGroup);

      toWrite.removeAll(orgGroup);
      toDelete.removeAll(newGroup);

      for (Integer i : toWrite) {
        writeStmt.setInt(1, employee.getUserId());
        writeStmt.setInt(2, i);
        writeStmt.executeUpdate();
      }

      for (Integer i : toDelete) {
        deleteStmt.setInt(1, employee.getUserId());
        deleteStmt.setInt(2, toDelete.get(i));
        deleteStmt.executeUpdate();
      }

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int updateEmployeeWing(Employee employee, ArrayList<Integer> newWing) {
    try(
        Connection con = ConMan.getConnection();

    ) {
      PreparedStatement writeStmt = ConMan.prepStat(con, Statements.DEF_ADD_WING_TO_EMPLOYEE);
      PreparedStatement deleteStmt = ConMan.prepStat(con, Statements.DEF_DELETE_WING_FROM_EMPLOYEE);
      ArrayList<Wing> temp = dbr.readWingIDsByUserID(employee.getUserId());
      ArrayList<Integer> orgWing = new ArrayList<>();

      for (Wing w: temp) {
        orgWing.add(w.getWingID());
      }

      List<Integer> toWrite = new ArrayList<>(newWing);
      List<Integer> toDelete = new ArrayList<>(orgWing);

      toWrite.removeAll(orgWing);
      toDelete.removeAll(newWing);

      for (Integer i : toWrite) {
        writeStmt.setInt(1, employee.getUserId());
        writeStmt.setInt(2, toWrite.get(i));
        writeStmt.executeUpdate();
      }

      for (Integer i : toDelete) {
        deleteStmt.setInt(1, employee.getUserId());
        deleteStmt.setInt(2, toDelete.get(i));
        deleteStmt.executeUpdate();
      }

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

}
