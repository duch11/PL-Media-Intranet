package plmedia.intranet.model;

/**
 * Defining Allergen objects.
 * @author Simon le Févre Ryom
 */
public class Allergen {

  private int allergenID;
  private String allergenName;
  private String allergenDescription;

  public Allergen(int allergenID, String allergenName, String allergenDescription) {
    this.allergenID = allergenID;
    this.allergenName = allergenName;
    this.allergenDescription = allergenDescription;
  }

  public Allergen() {
  }

  public int getAllergenID() {
    return allergenID;
  }

  public void setAllergenID(int allergenID) {
    this.allergenID = allergenID;
  }

  public String getAllergenName() {
    return allergenName;
  }

  public void setAllergenName(String allergenName) {
    this.allergenName = allergenName;
  }

  public String getAllergenDescription() {
    return allergenDescription;
  }

  public void setAllergenDescription(String allergenDescription) {
    this.allergenDescription = allergenDescription;
  }
}
