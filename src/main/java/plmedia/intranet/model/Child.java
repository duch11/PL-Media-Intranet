package plmedia.intranet.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Andreas Nissen
 */

public class Child {


  private String firstName;
  private String lastName;
  private Date birthday;
  private int childId;
  private String address;
  private int wingId;
  private int nap;
  private String trustedPickupPerson;
  private String otherInfo;
  private ArrayList<Parent> parents;

  public Child(String firstName, String lastName, Date birthday, int childId, String address,
      int wingId, int nap, String trustedPickupPerson, String otherInfo,
      ArrayList<Parent> parents) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.childId = childId;
    this.address = address;
    this.wingId = wingId;
    this.nap = nap;
    this.trustedPickupPerson = trustedPickupPerson;
    this.otherInfo = otherInfo;
    this.parents = parents;
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

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public int getchildId() {
    return childId;
  }

  public void setchildId(int id) {
    childId = id;
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

  public int getNap() {
    return nap;
  }

  public void setNap(int nap) {
    this.nap = nap;
  }

  public String getTrustedPickupPerson() {
    return trustedPickupPerson;
  }

  public void setTrustedPickupPerson(String trustedPickupPerson) {
    this.trustedPickupPerson = trustedPickupPerson;
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
}
