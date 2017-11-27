package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Andreas Nissen
 * @author Tobias Thomsen
 */

public class Employee extends User {

  private int wingID;
  private int group;

  public Employee(int userId, String password, String userEmail, String firstName, String lastName, ArrayList<String> permissions) {
    super(userId, password, userEmail, firstName, lastName, permissions);
  }
}
