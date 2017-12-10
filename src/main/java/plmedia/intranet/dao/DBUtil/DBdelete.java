package plmedia.intranet.dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Wing;

public class DBdelete {

  public int deleteParent(Parent parent) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      // Know if they can use the som "con" else make more "con"
      PreparedStatement dParentStmt = ConMan.prepStat(con,Statements.DEF_DELETE_USER_BY_ID);
      PreparedStatement dParentToChildStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_CHILD_FROM_PARENT);
      PreparedStatement dParentPermissionsStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_PERMISSION_FROM_USER);

      dParentPermissionsStmt.setInt(1, parent.getUserId());
      dParentPermissionsStmt.executeUpdate();

      dParentToChildStmt.setInt(1, parent.getUserId());
      dParentToChildStmt.executeUpdate();

      dParentStmt.setInt(1, parent.getUserId());
      dParentStmt.executeUpdate();

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int deleteEmployee(Employee employee) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      // Know if they can use the som "con" else make more "con"
      PreparedStatement dEmployeeStmt = ConMan.prepStat(con, Statements.DEF_DELETE_USER_BY_ID);
      PreparedStatement dEmployeeWingStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_WING_FROM_EMPLOYEE);
      PreparedStatement dEmployeeGroupStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_GROUP_FROM_EMPLOYEE);
      PreparedStatement dEmployeePermissionsStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_PERMISSION_FROM_USER);

      dEmployeePermissionsStmt.setInt(1, employee.getUserId());
      dEmployeePermissionsStmt.executeUpdate();

      dEmployeeWingStmt.setInt(1, employee.getUserId());
      dEmployeeWingStmt.executeUpdate();

      dEmployeeGroupStmt.setInt(1, employee.getUserId());
      dEmployeeGroupStmt.executeUpdate();

      dEmployeeStmt.setInt(1, employee.getUserId());
      dEmployeeStmt.executeUpdate();

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int deleteChild(Child child) {
    try(
        Connection con = ConMan.getConnection();
     ) {
      // Know if they can use the som "con" else make more "con"
      PreparedStatement dChildStmt = ConMan.prepStat(con, Statements.DEF_DELETE_CHILD_BY_ID);
      PreparedStatement dChildParentStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_PARENT_FROM_CHILD);
      PreparedStatement dChildAllergenStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_ALLERGEN_FROM_CHILD);

      dChildAllergenStmt.setInt(1, child.getChildId());
      dChildAllergenStmt.executeUpdate();

      dChildParentStmt.setInt(1, child.getChildId());
      dChildParentStmt.executeUpdate();

      dChildStmt.setInt(1, child.getChildId());
      dChildStmt.executeUpdate();

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int deleteWing(Wing wing) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      // Know if they can use the som "con" else make more "con"
      PreparedStatement dWingStmt = ConMan.prepStat(con, Statements.DEF_UPDATE_WING);
      PreparedStatement dWingEmpStmt = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_EMPLOYEE_FROM_WING);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int deleteGroup(Group group) {
    try(
        Connection con = ConMan.getConnection();
    ) {
      // Know if they can use the som "con" else make more "con"
      PreparedStatement dGroupStmt = ConMan.prepStat(con, Statements.DEF_DELETE_GROUP_BY_ID);
      PreparedStatement dGroupToEmp = ConMan.prepStat(con, Statements.DEF_DELETE_ALL_EMPLOYEE_FROM_GROUP);
      dGroupToEmp.setInt(1, group.getId());
      dGroupToEmp.executeUpdate();

      dGroupStmt.setInt(1, group.getId());
      dGroupStmt.executeUpdate();

      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

}
