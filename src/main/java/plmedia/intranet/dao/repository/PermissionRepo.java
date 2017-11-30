package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtil.PermissionsUtil;
import plmedia.intranet.model.Permission;

public class PermissionRepo {

  PermissionsUtil putil = new PermissionsUtil();

  public Permission readPermissionByID(int id){
    return putil.readPermissionByID(id);
  }

  public ArrayList<Permission> readAllPermissions(){
    return putil.readAllPermissions();
  }

  public ArrayList<Permission> readPermissionsByUserID(int id){
    return putil.readPermissionsByUserID(id);
  }

}
