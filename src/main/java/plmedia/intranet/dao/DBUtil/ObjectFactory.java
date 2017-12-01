package plmedia.intranet.dao.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import plmedia.intranet.model.Child;
import plmedia.intranet.model.Employee;
import plmedia.intranet.model.Group;
import plmedia.intranet.model.Parent;
import plmedia.intranet.model.Permission;
import plmedia.intranet.model.Wing;

public class ObjectFactory  {

  public Parent makeParent(ResultSet rs){

    return null;
  }

  public Employee makeEmployee(ResultSet rs){

    return null;
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
