package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Simon le Févre Ryom
 * @author Andreas Nissen
 */

public class Wing {

  private String wingName;
  private String wingDescription;
  private ArrayList<String> child;
  private ArrayList<String> employee;

  public Wing(String wingName, String wingDescription, ArrayList<String> child,
      ArrayList<String> employee) {
    this.wingName = wingName;
    this.wingDescription = wingDescription;
    this.child = child;
    this.employee = employee;
  }

  public Wing(String wingName, String wingDescription) {
    this.wingName = wingName;
    this.wingDescription = wingDescription;
  }
}
