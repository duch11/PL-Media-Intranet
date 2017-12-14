package plmedia.intranet.dao.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Permission;

/**
 * @author Simon le Févre Ryom
 */
@Repository
public class PermissionRepo {

  DBread dbr = new DBread();
  DBupdate dbu = new DBupdate();

  public Permission readPermissionByID(int id){
    return dbr.readPermissionByID(id);
  }

  public ArrayList<Permission> readAllPermissions(){
    return dbr.readAllPermissions();
  }

  public ArrayList<Permission> readPermissionsByUserID(int id){
    return dbr.readPermissionsByUserID(id);
  }

  public int updatePermissionByID(Employee employee, ArrayList<Integer> newPermission){
    return dbu.updatePermissionByID(employee, newPermission);
  }
}
