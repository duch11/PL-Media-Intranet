package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Simon le Févre Ryom
 * @author Andreas Nissen
 */

public class Wing {

  private int wingID;
  private String wingName;
  private String wingDescription;
  private ArrayList<String> child;
  private ArrayList<String> employee;

  public Wing(int wingID, String wingName, String wingDescription, ArrayList<String> child,
      ArrayList<String> employee) {
    this.wingName = wingName;
    this.wingDescription = wingDescription;
    this.child = child;
    this.employee = employee;
  }

  public Wing(int wingID, String wingName, String wingDescription) {
    this.wingName = wingName;
    this.wingDescription = wingDescription;
  }

  public String getWingName() {
    return wingName;
  }

  public String getWingDescription() {
    return wingDescription;
  }
}
