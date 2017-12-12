package plmedia.intranet.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Andreas Nissen
 * @author Simon le Févre Ryom
 */

public class Child {


  private int childId;
  private String firstName;
  private String lastName;
  private java.sql.Date birthday;
  private String address;
  private int wingId;
  private String otherInfo;
  private ArrayList<Parent> parents;
  private ArrayList<Allergen> allergens;


  public Child() {
  }

  public Child(int childId, String firstName, String lastName, java.sql.Date birthday,
      String address, int wingId, String otherInfo, ArrayList<Parent> parents,
      ArrayList<Allergen> allergens) {
    this.childId = childId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.address = address;
    this.wingId = wingId;
    this.otherInfo = otherInfo;
    this.parents = parents;
    this.allergens = allergens;
  }

  public Child(int childId, String firstName, String lastName, java.sql.Date birthday,
      String address, int wingId, String otherInfo, ArrayList<Parent> parents) {
    this.childId = childId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.address = address;
    this.wingId = wingId;
    this.otherInfo = otherInfo;
    this.parents = parents;
  }

  public Child(String firstName, String lastName, java.sql.Date birthday, String address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.address = address;
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

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
  }

  public int getChildId() {
    return childId;
  }

  public void setChildId(int childId) {
    this.childId = childId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getWingId() {
    return wingId;
  }

  public void setWingId(int wingId) {
    this.wingId = wingId;
  }

  public String getOtherInfo() {
    return otherInfo;
  }

  public void setOtherInfo(String otherInfo) {
    this.otherInfo = otherInfo;
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

  public void setParents(ArrayList<Parent> parents) {
    this.parents = parents;
  }

  public ArrayList<Allergen> getAllergens() {
    return allergens;
  }

  public void setAllergens(ArrayList<Allergen> allergens) {
    this.allergens = allergens;
  }


  @Override
  public String toString() {
    return "Child{" +
        "childId=" + childId +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", birthday=" + birthday +
        ", address='" + address + '\'' +
        ", wingId=" + wingId +
        ", otherInfo='" + otherInfo + '\'' +
        ", parents=" + parents +
        ", allergens=" + allergens +
        '}';
  }
}

