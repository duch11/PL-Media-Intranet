package plmedia.intranet.model;

import java.util.ArrayList;
import java.util.Date;

public class Child {


  private String firstName;
  private String lastName;
  private Date birthday;
  private int Id;
  private String address;
  private String wingId;
  private int nap;
  private String trustedPickupPerson;
  private String otherInfo;
  private ArrayList<Parent> parents;

  public Child(String firstName, String lastName, Date birthday, int id, String address,
      String wingId, int nap, String trustedPickupPerson, String otherInfo,
      ArrayList<Parent> parents) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.Id = id;
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

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWingId() {
    return wingId;
  }

  public void setWingId(String wingId) {
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
