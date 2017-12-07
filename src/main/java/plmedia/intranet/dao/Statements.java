package plmedia.intranet.dao;

import org.omg.PortableInterceptor.ServerRequestInfo;

/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

public class Statements {

  /*
   Getters
    */
  public static final String DEF_GET_ALL_USERS_BY_TYPE_SQL = "SELECT * FROM user WHERE type=?";

  public static final String DEF_GET_USERS_BY_TYPE_AND_ID_SQL = "SELECT * FROM user WHERE type=? AND user_id=?";
  public static final String DEF_GET_ALL_CHILDREN_SQL = "SELECT * FROM child";
  public static final String DEF_GET_PARENT_BY_ID_SQL = "SELECT * FROM user WHERE type=\"par\" AND user_id=\"?\"";
  public static final String DEF_GET_CHILD_BY_ID_SQL = "SELECT * FROM child WHERE child_id = ?";
  public static final String DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL = "{CALL GetChildrenByParentID(?)}";
  public static final String DEF_GET_ALL_EMAIL = "SELECT user_email FROM user";

  // EMPLOYEE
  public static final String DEF_GET_EMPLOYEE_BY_ID_SQL = "SELECT * FROM user WHERE type=\"emp\" AND user_id=\"?\" ";



  // Permissions
  public static final String DEF_GET_PERMISSION_ID_BY_USER_ID_SQL = "SELECT fk_permission_id FROM user_permission WHERE fk_user_id = ?";
  public static final String DEF_GET_PERMISSIONS_BY_ID_SQL = "SELECT * FROM permission WHERE permission_id = ?";
  public static final String DEF_GET_ALL_PERMISSIONS_SQL = "SELECT * FROM permission";

  // Groups
  public static final String DEF_GET_ALL_EMPLOYEES_BY_GROUP_ID = "SELECT * FROM user WHERE user_id = ANY (SELECT fk_employee_user_id FROM employee_user_group WHERE fk_group_id = ?)";
  public static final String DEF_GET_GROUP_BY_ID = "SELECT * FROM intranetdb.group WHERE group_id = ?";
  public static final String DEF_GET_ALL_GROUPS = "SELECT * FROM intranetdb.group";


  // Wings
  public static final String DEF_GET_WING_BY_ID = "SELECT * FROM wing WHERE wing id = ?";
  public static final String DEF_GET_ALL_WINGS = "SELECT * FROM wing";



  // Updates
  public static final String DEF_UPDATE_USER = "UPDATE user SET password = ?, user_email = ?, first_name = ?, last_name = ? where user_id = ?";
  public static final String DEF_UPDATE_CHILD = "UPDATE child SET ";

  public static final String DEF_UPDATE_PERMISSION_BY_ID = "UPDATE user_permission SET ";





  // Setters / Creates
  public static final String DEF_CREATE_PARENT_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_EMPLOYEE_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_CHILD_SQL = "INSERT INTO child (first_name, last_name, birthday, address, fk_wing_id) VALUES(?,?,?,?,?)";
  public static final String DEF_CREATE_WING_SQL = "INSERT INTO wing (wing_name, wing_description) VALUES (?,?)";
  public static final String DEF_CREATE_GROUP_SQL = "INSERT INTO group (group_name, group_description) VALUES (?,?)";

  // Deletes

}
