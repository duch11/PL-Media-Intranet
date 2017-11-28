package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Andreas Nissen
 */

public abstract class User {

  private int userId;
  private String password;
  private String userEmail;
  private String firstName;
  private String lastName;
  private ArrayList<String> permissions;

  public User() {
  }

  public User(int userId, String password, String userEmail, String firstName,
      String lastName, ArrayList<String> permissions) {
    this.userId = userId;
    this.password = password;
    this.userEmail = userEmail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.permissions = permissions;
  }

  public User(String password, String userEmail, String firstName, String lastName) {
    this.password = password;
    this.userEmail = userEmail;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public ArrayList<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(ArrayList<String> permissions) {
    this.permissions = permissions;
  }


  // To test populating results
  @Override
  public String toString(){
    return userId + ": " + firstName + " " + lastName + " has email: "+ userEmail + " and password: \"" + password + "\" with permissions: " + permissions;
  }


}
