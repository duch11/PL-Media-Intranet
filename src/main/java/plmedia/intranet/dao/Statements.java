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
  public static final String DEF_GET_PARENT_BY_ID_SQL = "SELECT * FROM user WHERE type=\"ROLE_PAR\" AND user_id=\"?\"";
  public static final String DEF_GET_PARENT_BY_EMAIL_SQL = "SELECT * FROM user WHERE type=\"ROLE_PAR\" AND user_email=?";
  public static final String DEF_GET_CHILD_BY_ID_SQL = "SELECT * FROM child WHERE child_id = ?";
  public static final String DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL = "SELECT fk_child_id FROM parent_user_child WHERE fk_parent_user_id=?";
  public static final String DEF_GET_ALL_EMAIL = "SELECT user_email FROM user";

  // EMPLOYEE
  public static final String DEF_GET_EMPLOYEE_BY_ID_SQL = "SELECT * FROM user WHERE type=\"ROLE_EMP\" AND user_id= ? ";



  // Permissions
  public static final String DEF_GET_PERMISSION_ID_BY_USER_ID_SQL = "SELECT fk_permission_id FROM user_permission WHERE fk_user_id = ?";
  public static final String DEF_GET_PERMISSIONS_BY_ID_SQL = "SELECT * FROM permission WHERE permission_id = ?";
  public static final String DEF_GET_ALL_PERMISSIONS_SQL = "SELECT * FROM permission";

  // Groups
  public static final String DEF_GET_ALL_EMPLOYEES_BY_GROUP_ID = "SELECT * FROM user WHERE user_id = ANY (SELECT fk_employee_user_id FROM employee_user_group WHERE fk_group_id = ?)";
  public static final String DEF_GET_GROUP_BY_ID = "SELECT * FROM intranetdb.group WHERE group_id = ?";
  public static final String DEF_GET_ALL_GROUPS = "SELECT * FROM intranetdb.group";
  public static final String DEF_GET_GROUP_BY_USER_ID = "SELECT * FROM intranetdb.group where group_id = (SELECT fk_group_id FROM employee_user_group WHERE fk_employee_user_id = ?)";


  // Wings
  public static final String DEF_GET_WING_BY_ID = "SELECT * FROM wing WHERE wing_id = ?";
  public static final String DEF_GET_ALL_WINGS = "SELECT * FROM wing";

  // Allergen
  public static final String DEF_GET_ALLERGEN_BY_ID = "SELECT * FROM allergen WHERE allergen_id = ?";
  public static final String DEF_GET_ALL_ALLERGENS = "SELECT * FROM intranetdb.allergen";
  public static final String DEF_GET_ALLERGEN_BY_CHILD_ID = "SELECT * FROM intranetdb.allergen where allergen_id = (SELECT fk_allergen_id FROM child_allergen WHERE fk_child_id = ?)";



  // Updates
  public static final String DEF_UPDATE_USER = "UPDATE user SET password = ?, user_email = ?, first_name = ?, last_name = ? where user_id = ?";
  public static final String DEF_UPDATE_CHILD = "UPDATE child SET ";


  //FK add and delete
  public static final String DEF_ADD_CHILD_TO_PARENT = "INSERT INTO parent_user_child (fk_parent_user_id, fk_child_id) VALUES (?,?)";
  public static final String DEF_ADD_PERMISSION_TO_USER = "INSERT INTO user_permission (fk_user_id, fk_permission_id) VALUE (?,?)";
  public static final String DEF_ADD_ALLERGEN_TO_CHILD = "INSERT INTO child_allergen (fk_child_id, fk_allergen_id) VALUE (?,?)";

  public static final String DEF_DELETE_CHILD_FROM_PARENT = "DELETE FROM parent_user_child WHERE (fk_parent_user_id, fk_child_id) = (?,?)";
  public static final String DEF_DELETE_PERMISSION_FROM_USER = "DELETE FROM user_permission WHERE (fk_user_id, fk_permission_id) = (?,?)";
  public static final String DEF_DELETE_ALLERGEN_FROM_CHILD = "DELETE FROM child_allergen (fk_child_id, fk_allergen_id) = (?,?)";
  // Setters / Creates
  public static final String DEF_CREATE_PARENT_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_EMPLOYEE_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_CHILD_SQL = "INSERT INTO child (first_name, last_name, birthday, address, fk_wing_id) VALUES(?,?,?,?,?)";
  public static final String DEF_CREATE_WING_SQL = "INSERT INTO wing (wing_name, wing_description) VALUES (?,?)";
  public static final String DEF_CREATE_GROUP_SQL = "INSERT INTO group (group_name, group_description) VALUES (?,?)";
  public static final String DEF_CREATE_ALLERGEN_SQL = "INSERT INTO allergen (allergen_name, allergen_description) VALUES (?,?)";

  // Deletes


}
