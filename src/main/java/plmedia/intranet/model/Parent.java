package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Andreas Nissen
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 * @author Jonas Ladefoged Holm
 */

public class Parent extends User {

  private ArrayList<Child> children;

  public Parent() {

  }

  public Parent(int userId, String password, String userEmail, String firstName, String lastName, ArrayList<Permission> permissions) {
    super(userId, password, userEmail, firstName, lastName, permissions);
  }

  public Parent(String password, String userEmail, String firstName, String lastName) {
    super(password, userEmail, firstName, lastName);
  }

  public ArrayList<Child> getChildren() {
    return children;
  }

  public void setChildren(ArrayList<Child> children) {
    this.children = children;
  }
}
