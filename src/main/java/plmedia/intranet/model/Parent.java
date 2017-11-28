package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Andreas Nissen
 * @author Tobias Thomsen
 */

public class Parent extends User {

  public Parent(int userId, String password, String userEmail, String firstName, String lastName, ArrayList<String> permissions) {
    super(userId, password, userEmail, firstName, lastName, permissions);
  }

  public Parent(String password, String userEmail, String firstName, String lastName) {
    super(password, userEmail, firstName, lastName);
  }
}
