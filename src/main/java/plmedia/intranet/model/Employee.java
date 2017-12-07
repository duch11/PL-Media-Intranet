package plmedia.intranet.model;


import java.util.ArrayList;

/**
 * @author Andreas Nissen
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

public class Employee extends User {

  private int wingID;
  private Group group;

  public Employee() {
  }

  public Employee(int userId, String password, String userEmail, String firstName, String lastName, Group group, ArrayList<Permission> permissions) {
    super(userId, password, userEmail, firstName, lastName, permissions);
    this.group = group;
  }

  public Employee(String password, String userEmail, String firstName, String lastName, Group group) {
    super(password, userEmail, firstName, lastName);
    this.group = group;
  }


  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
