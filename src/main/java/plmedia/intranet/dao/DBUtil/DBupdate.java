package plmedia.intranet.dao.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;

public class DBupdate {

  DBread dbr = new DBread();

  public int updateChildToParent(Parent parent, ArrayList<Integer> newChildren) {
    try (
        PreparedStatement writeStmt = ConMan.prepStat(Statements.DEF_ADD_CHILD_TO_PARENT);
        PreparedStatement deleteStmt = ConMan.prepStat(Statements.DEF_DELETE_CHILD_FROM_PARENT);
    ) {

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
        PreparedStatement writeStmt = ConMan.prepStat(Statements.DEF_ADD_PERMISSION_TO_USER);
        PreparedStatement deleteStmt = ConMan.prepStat(Statements.DEF_DELETE_PERMISSION_FROM_USER)
    ) {
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
        writeStmt.setInt(2, toWrite.get(i));
        writeStmt.executeUpdate();
      }

      for (Integer i: toDelete) {
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

  public int updateChildAllergens(Child child, ArrayList<Integer>)
}
