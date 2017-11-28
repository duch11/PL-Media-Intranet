package plmedia.intranet.dao;

/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

public class Statements {

  // Getters
  public static final String DEF_GET_ALL_USERS_BY_TYPE_SQL = "SELECT * FROM user WHERE type=?";
  public static final String DEF_GET_USERS_BY_TYPE_AND_ID_SQL = "SELECT * FROM user WHERE type=? AND user_id=?";

  public static final String DEF_GET_ALL_CHILDREN_SQL = "SELECT * FROM child";

  public static final String DEF_GET_PARENT_BY_ID_SQL = "SELECT * FROM user WHERE type=\"par\" AND user_id=\"?\"";
  public static final String DEF_GET_EMPLOYEE_BY_ID_SQL = "SELECT * FROM user WHERE type=\"emp\" AND user_id=\"?\" ";
  public static final String DEF_GET_CHILD_BY_ID_SQL = "SELECT * FROM child WHERE child_id = ?";

  public static final String DEF_GET_PERMISSIONS_BY_ID_SQL = "SELECT fk_permission_id FROM user_permission WHERE fk_user_id = ?";
  public static final String DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL = "{CALL GetChildrenByParentID(?)}";

  public static final String DEF_GET_ALL_EMAIL = "SELECT user_email FROM user";

  // Updates


  // Setters / Creates
  public static final String DEF_CREATE_PARENT_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
  public static final String DEF_CREATE_EMPLOYEE_USER_SQL = "INSERT INTO user (password, user_email, first_name, last_name, type, enabled) VALUES(?,?,?,?,?,?)";
}
