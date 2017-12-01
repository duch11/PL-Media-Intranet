package plmedia.intranet.dao.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import plmedia.intranet.dao.repository.PermissionRepo;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;
import plmedia.intranet.model.Wing;

public class ObjectFactory  {

  PermissionRepo permissionRepo = new PermissionRepo();

  public Parent makeParent(ResultSet rs) throws SQLException{
    ArrayList<Permission> permissions;
    permissions = permissionRepo.readPermissionsByUserID((rs.getInt("user_id")));
    return new Parent(
        rs.getInt("user_id"),
        rs.getString("password"),
        rs.getString("user_email"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        permissions);
  }

  public Employee makeEmployee(ResultSet rs) throws SQLException{
    ArrayList<Permission> permissions;
    permissions = permissionRepo.readPermissionsByUserID((rs.getInt("user_id")));
    return new Employee(
        rs.getInt("user_id"),
        rs.getString("password"),
        rs.getString("user_email"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        permissions);
  }

  public Child makeChild(ResultSet rs) throws SQLException {

    return new Child(
        rs.getInt("child_id"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        rs.getDate("birthday"),
        rs.getString("address"),
        rs.getInt("fk_wing_id"),
        null ,
        null,
        null,
        null);
  }

  public Wing makeWing(ResultSet rs) throws SQLException {
    return new Wing(
        rs.getInt(1),
        rs.getString(2),
        rs.getString(3));
  }

  public Group makeGroup(ResultSet rs) throws SQLException {

    return new Group(
        rs.getInt(1),
        rs.getString(2),
        rs.getString(3));
  }

  public Permission makePermission(ResultSet rs) throws SQLException  {
    return new Permission(
        rs.getInt(1),
        rs.getString(2),
        rs.getString(3));

  }
}
