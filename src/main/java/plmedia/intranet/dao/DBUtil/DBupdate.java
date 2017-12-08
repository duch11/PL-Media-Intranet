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
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;

public class DBupdate {

  DBread dbr = new DBread();

  public int updateChildToParent(Parent parent, ArrayList<Integer> newChildren) {
    try (
        PreparedStatement addStmt = ConMan.prepStat(Statements.DEF_ADD_CHILD_TO_PARENT);
        PreparedStatement deleteStmt = ConMan.prepStat(Statements.DEF_DELETE_CHILD_FROM_PARENT);
    ) {

      ArrayList<Integer> orgChildren = dbr.readChildrenIDByParentID(parent.getUserId());

      List<Integer> toWrite = new ArrayList<>(newChildren);
      List<Integer> toDelete = new ArrayList<>(orgChildren);

      toWrite.removeAll(orgChildren);
      toDelete.removeAll(newChildren);

      for (Integer i : toWrite) {
        addStmt.setInt(1, parent.getUserId());
        addStmt.setInt(2, toWrite.get(i));
        addStmt.executeUpdate();
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
/*
  public void updatePermissionByID(int id) {
    try (
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_UPDATE_PERMISSION_BY_ID);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  */
}
