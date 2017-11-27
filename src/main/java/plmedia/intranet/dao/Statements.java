package plmedia.intranet.dao;

public class Statements {

  public static final String DEF_GET_ALL_PARENTS_SQL = "SELECT * FROM user WHERE type=\"par\"";
  public static final String DEF_GET_ALL_EMPLOYEE_SQL = "SELECT * FROM user WHERE type=\"emp\"";
  public static final String DEF_GET_ALL_CHILDREN_SQL = "SELECT * FROM child";




  public static final String DEF_GET_PERMISSIONS_BY_ID_SQL = "SELECT fk_permission_id FROM user_permission WHERE fk_user_id = ?";
  public static final String DEF_GET_CHILDREN_ID_BY_PARENT_ID_SQL = "{CALL GetChildrenByParentID(?)}";
  public static final String DEF_GET_CHILD_BY_ID_SQL = "SELECT * FROM child WHERE child_id = ?";
}
