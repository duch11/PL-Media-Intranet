package plmedia.intranet.dao;

/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 * @author Jonas Holm
 */
public class Statements {

  // Getters
  public static final String DEF_GET_ALL_USERS_BY_TYPE_SQL = "SELECT * FROM user WHERE type=?";

  // Parent
  public static final String DEF_GET_PARENT_BY_ID_SQL = "SELECT * FROM user WHERE type=\"ROLE_PAR\" AND user_id=?";
  public static final String DEF_GET_PARENT_BY_EMAIL_SQL = "SELECT * FROM user WHERE type=\"ROLE_PAR\" AND user_email=?";
  public static final String DEF_GET_PARENT_ID_BY_CHILD_ID_SQL = "SELECT fk_parent_user_id FROM parent_user_child WHERE fk_child_id = ?";

  // Child
  public static final String DEF_GET_CHILDREN_BY_PARENT_ID_SQL = "SELECT * FROM child INNER JOIN parent_user_child ON child_id = fk_child_id WHERE fk_parent_user_id=?";
  public static final String DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL = "SELECT fk_child_id FROM parent_user_child WHERE fk_parent_user_id=?";
  public static final String DEF_GET_CHILDREN_BY_WING_ID_SQL = "SELECT * FROM child INNER JOIN child_wing ON child_id = child_wing.fk_child_id WHERE fk_wing_id=?";
  public static final String DEF_GET_ALL_CHILDREN_SQL = "SELECT * FROM child";
  public static final String DEF_GET_CHILD_BY_ID_SQL = "SELECT * FROM child WHERE child_id = ?";
  public static final String DEF_GET_NOT_CHILDREN_BY_PARENT_ID_SQL = "SELECT * FROM child WHERE child_id NOT IN (SELECT fk_child_id FROM parent_user_child WHERE fk_parent_user_id=?)";

  // Util
  public static final String DEF_GET_ALL_EMAIL = "SELECT user_email FROM user";
  public static final String DEF_GET_PASSWORD_BY_USER_ID = "SELECT password FROM user WHERE user_id = ?";

  // EMPLOYEE
  public static final String DEF_GET_EMPLOYEE_BY_EMAIL_SQL = "SELECT * FROM user WHERE type=\"ROLE_EMP\" AND user_email=?";
  public static final String DEF_GET_EMPLOYEE_BY_ID_SQL = "SELECT * FROM user WHERE type=\"ROLE_EMP\" AND user_id= ? ";

  // Permissions
  public static final String DEF_GET_PERMISSION_ID_BY_USER_ID_SQL = "SELECT fk_permission_id FROM user_permission WHERE fk_user_id = ?";
  public static final String DEF_GET_PERMISSIONS_BY_ID_SQL = "SELECT * FROM permission WHERE permission_id = ?";
  public static final String DEF_GET_ALL_PERMISSIONS_SQL = "SELECT * FROM permission";

  // Groups
  public static final String DEF_GET_ALL_EMPLOYEES_BY_GROUP_ID = "SELECT * FROM user WHERE user_id = ANY (SELECT fk_employee_user_id FROM employee_user_group WHERE fk_group_id = ?)";
  public static final String DEF_GET_GROUP_BY_ID = "SELECT * FROM intranetdb.group WHERE group_id = ?";
  public static final String DEF_GET_ALL_GROUPS = "SELECT * FROM intranetdb.group";
  public static final String DEF_GET_GROUP_BY_USER_ID = "SELECT * FROM intranetdb.group WHERE group_id = (SELECT fk_group_id FROM employee_user_group WHERE fk_employee_user_id = ?)";
  public static final String DEF_GET_GROUPS_IDS_BY_USER_ID = "SELECT fk_group_id FROM employee_user_group WHERE fk_employee_user_id=?";

  // Wings
  public static final String DEF_GET_WING_BY_ID = "SELECT * FROM wing WHERE wing_id = ?";
  public static final String DEF_GET_ALL_WINGS = "SELECT * FROM wing";
  public static final String DEF_GET_WING_BY_USER_ID = "SELECT * FROM intranetdb.wing WHERE wing_id = (SELECT fk_wing_id FROM employee_user_wing WHERE fk_employee_user_id = ?)";
  public static final String DEF_GET_WING_IDS_BY_USER_ID = "SELECT fk_wing_id FROM employee_user_wing WHERE fk_employee_user_id = ?";
  public static final String DEF_GET_WING_IDS_BY_CHILD_ID = "SELECT fk_wing_id FROM child_wing WHERE fk_child_id = ?";
  public static final String DEF_GET_WING_BY_child_ID = "SELECT * FROM intranetdb.wing WHERE wing_id = (SELECT fk_wing_id FROM child_wing WHERE fk_child_id = ?)";

  // Allergen
  public static final String DEF_GET_ALLERGEN_BY_ID = "SELECT * FROM allergen WHERE allergen_id = ?";
  public static final String DEF_GET_ALL_ALLERGENS = "SELECT * FROM intranetdb.allergen";
  public static final String DEF_GET_ALLERGEN_BY_CHILD_ID = "SELECT fk_allergen_id FROM child_allergen WHERE fk_child_id = ?";

  // Updates
  public static final String DEF_UPDATE_USER = "UPDATE user SET password = ?, user_email = ?, first_name = ?, last_name = ? WHERE user_id = ?";
  public static final String DEF_UPDATE_USER_NOPASS = "UPDATE user SET user_email = ?, first_name = ?, last_name = ? WHERE user_id = ?";
  public static final String DEF_UPDATE_CHILD = "UPDATE child SET first_name = ?, last_name = ?, birthday = ?, address = ? WHERE child_id = ?";
  public static final String DEF_UPDATE_WING = "UPDATE wing SET wing_name = ?, wing_description = ? WHERE wing_id = ?";
  public static final String DEF_UPDATE_GROUP = "UPDATE intranetdb.group SET group_name = ?, group_description = ? WHERE group_id = ?";
  public static final String DEF_UPDATE_ALLERGEN = "UPDATE intranetdb.allergen SET allergen_name = ?, allergen_description = ? WHERE allergen_id = ?";

  //FK add and delete
  public static final String DEF_ADD_CHILD_TO_PARENT = "INSERT INTO parent_user_child (fk_parent_user_id, fk_child_id) VALUES (?,?)";
  public static final String DEF_ADD_PERMISSION_TO_USER = "INSERT INTO user_permission (fk_user_id, fk_permission_id) VALUES (?,?)";
  public static final String DEF_ADD_ALLERGEN_TO_CHILD = "INSERT INTO child_allergen (fk_child_id, fk_allergen_id) VALUES (?,?)";
  public static final String DEF_ADD_GROUP_TO_EMPLOYEE = "INSERT INTO employee_user_group (fk_employee_user_id, fk_group_id) VALUES (?,?)";
  public static final String DEF_ADD_WING_TO_EMPLOYEE = "INSERT INTO employee_user_wing (fk_employee_user_id, fk_wing_id) VALUES (?,?)";
  public static final String DEF_ADD_WING_TO_CHILD = "INSERT INTO child_wing (fk_child_id, fk_wing_id) VALUES (?,?)";

  public static final String DEF_DELETE_CHILD_FROM_PARENT = "DELETE FROM parent_user_child WHERE (fk_parent_user_id, fk_child_id) = (?,?)";
  public static final String DEF_DELETE_ALL_CHILD_FROM_PARENT = "DELETE FROM parent_user_child WHERE (fk_parent_user_id) = (?)";
  public static final String DEF_DELETE_ALL_PARENT_FROM_CHILD = "DELETE FROM parent_user_child WHERE (fk_child_id) = (?)";

  public static final String DEF_DELETE_PERMISSION_FROM_USER = "DELETE FROM user_permission WHERE (fk_user_id, fk_permission_id) = (?,?)";
  public static final String DEF_DELETE_ALL_PERMISSION_FROM_USER = "DELETE FROM user_permission WHERE (fk_user_id) = (?)";

  public static final String DEF_DELETE_ALLERGEN_FROM_CHILD = "DELETE FROM child_allergen WHERE (fk_child_id, fk_allergen_id) = (?,?)";
  public static final String DEF_DELETE_ALL_ALLERGEN_FROM_CHILD = "DELETE FROM child_allergen WHERE (fk_child_id) = (?)";
  public static final String DEF_DELETE_ALL_CHILD_FROM_ALLERGEN = "DELETE FROM child_allergen WHERE (fk_allergen_id) = (?)";

  public static final String DEF_DELETE_GROUP_FROM_EMPLOYEE = "DELETE FROM employee_user_group WHERE (fk_employee_user_id, fk_group_id) = (?,?)";
  public static final String DEF_DELETE_ALL_GROUP_FROM_EMPLOYEE = "DELETE FROM employee_user_group WHERE (fk_employee_user_id) = (?)";
  public static final String DEF_DELETE_ALL_EMPLOYEE_FROM_GROUP = "DELETE FROM employee_user_group WHERE (fk_group_id) = (?)";

  public static final String DEF_DELETE_WING_FROM_EMPLOYEE = "DELETE FROM employee_user_wing WHERE (fk_user_id, fk_wing_id) = (?,?)";
  public static final String DEF_DELETE_ALL_WING_FROM_EMPLOYEE = "DELETE FROM employee_user_wing WHERE (fk_user_id) = (?)";
  public static final String DEF_DELETE_ALL_EMPLOYEE_FROM_WING = "DELETE FROM employee_user_wing WHERE (fk_wing_id) = (?)";
  public static final String DEF_DELETE_WING_FROM_CHILD = "DELETE FROM child_wing WHERE (fk_child_id, fk_wing_id) = (?,?)";
  public static final String DEF_DELETE_WING_FROM_CHILD_WING = "DELETE FROM child_wing WHERE (fk_wing_id) = (?)";

  // Creates
  public static final String DEF_CREATE_PARENT_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_EMPLOYEE_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_CHILD_SQL = "INSERT INTO child (first_name, last_name, birthday, address) VALUES(?,?,?,?)";
  public static final String DEF_CREATE_WING_SQL = "INSERT INTO wing (wing_name, wing_description) VALUES (?,?)";
  public static final String DEF_CREATE_GROUP_SQL = "INSERT INTO group (group_name, group_description) VALUES (?,?)";
  public static final String DEF_CREATE_ALLERGEN_SQL = "INSERT INTO allergen (allergen_name, allergen_description) VALUES (?,?)";

  // Deletes
  public static final String DEF_DELETE_USER_BY_ID = "DELETE FROM user WHERE user_id = ?";
  public static final String DEF_DELETE_CHILD_BY_ID = "DELETE FROM child WHERE child_id = ?";
  public static final String DEF_DELETE_WING_BY_ID = "DELETE FROM wing WHERE wing_id = ?";
  public static final String DEF_DELETE_GROUP_BY_ID = "DELETE FROM group WHERE group_id = ?";
  public static final String DEF_DELETE_ALLERGEN_BY_ID = "DELETE FROM allergen WHERE allergen_id = ?";
}
