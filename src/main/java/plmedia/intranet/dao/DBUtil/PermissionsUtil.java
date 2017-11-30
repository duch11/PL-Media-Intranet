package plmedia.intranet.dao.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import plmedia.intranet.dao.ConMan;
import plmedia.intranet.dao.Statements;
import plmedia.intranet.model.Permission;

public class PermissionsUtil {

  public Permission readPermissionByID(int id){
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_PERMISSIONS_BY_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.first();

      return new Permission(
          rs.getInt(1),
          rs.getString(2),
          rs.getString(3));

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }

  public ArrayList<Permission> readAllPermissions(){
    try(
        ResultSet rs = ConMan.regStat(Statements.DEF_GET_ALL_PERMISSIONS_SQL);
    ) {
      ArrayList<Permission> permissions = new ArrayList<>();
      while(rs.next()){

        permissions.add(readPermissionByID(rs.getInt(1)));

      }
      return permissions;

    } catch (SQLException e){
      e.printStackTrace();
    }

    return null;
  }

  public ArrayList<Permission> readPermissionsByUserID(int id){
    try(
        PreparedStatement stmt = ConMan.prepStat(Statements.DEF_GET_PERMISSION_ID_BY_USER_ID_SQL);
    ) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      ArrayList<Permission> permissions = new ArrayList<>();

      while (rs.next()) {
        permissions.add(readPermissionByID(rs.getInt("fk_permission_id")));
      }

      return permissions;

    } catch (SQLException e){
      e.printStackTrace();
    }
    return null; // Error code?
  }

}
