package plmedia.intranet.model;

import java.util.ArrayList;

/**
 * @author Andreas Nissen
 * @author Tobias Thomsen
 */

public class Parent extends User {

  private ArrayList<String> children;

  public Parent(int userId, String password, String userEmail, String firstName, String lastName, ArrayList<String> permissions) {
    super(userId, password, userEmail, firstName, lastName, permissions);
  }

  public void addChildren(ArrayList<String> children){
    this.children = children;
  }

  public void familyPhoto(){
    if(children != null){
      for (String s :
          children) {
        System.out.println(s);
      }
    } else {
      System.out.println("Kids are dead or never born!");
    }
  }
}
