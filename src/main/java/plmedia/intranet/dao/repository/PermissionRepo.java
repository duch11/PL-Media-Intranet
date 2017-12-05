package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.model.Permission;

public class PermissionRepo {

  DBread dbr = new DBread();

  public Permission readPermissionByID(int id){
    return dbr.readPermissionByID(id);
  }

  public ArrayList<Permission> readAllPermissions(){
    return dbr.readAllPermissions();
  }

  public ArrayList<Permission> readPermissionsByUserID(int id){
    return dbr.readPermissionsByUserID(id);
  }

}
