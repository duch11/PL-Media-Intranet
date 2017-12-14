package plmedia.intranet.model;

/**
 * @author Simon le Févre Ryom
 */
public class Permission {

  private int permissionID;
  private String permissionName;
  private String permissionDescription;

  public Permission() {
  }

  public Permission(int permissionID, String permissionName, String permissionDescription) {
    this.permissionID = permissionID;
    this.permissionName = permissionName;
    this.permissionDescription = permissionDescription;
  }

  public int getPermissionID() {
    return permissionID;
  }

  public String getPermissionName() {
    return permissionName;
  }

  public String getPermissionDescription() {
    return permissionDescription;
  }
}
