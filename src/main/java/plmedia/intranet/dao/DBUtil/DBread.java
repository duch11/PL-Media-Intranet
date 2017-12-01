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
import plmedia.intranet.model.Parent;

public class DBread {

  ObjectFactory ObjFac = new ObjectFactory();
  PermissionRepo permissionRepo = new PermissionRepo();

  public Parent readParentByID(int id){
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_PARENT_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();



    } catch (SQLException e){
      e.printStackTrace();
    }


    return null;
  }

  public ArrayList<Parent> readAllParents() {
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_ALL_USERS_BY_TYPE_SQL);
    ) {
      stmt.setString(1, "ROLE_PAR");
      ResultSet rs = stmt.executeQuery();

      ArrayList<Parent> parents = new ArrayList<>();

      while(rs.next()){
        parents.add(ObjFac.makeParent(rs));
      }

      return parents;

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }

  public Employee readEmployeeByID(int id){
    return null;
  }

  public ArrayList<Employee> readAllEmployees(){
    return null;
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

  public Child readChild(int id) {
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_CHILD_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      return ObjFac.makeChild(rs);

      } catch (SQLException e) {
      e.printStackTrace();
    }
    return null; // Error code?
  }


}
