package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.model.Allergen;

/**
 * A repository for Allergens. Takes in logic from DBUtil package.
 * @author Simon le Févre Ryom
 */
public class AllergenRepo implements IRepo<Allergen> {

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();

  @Override
  public int Create(Allergen allergen) {
    return dbc.createAllergen(allergen);
  }

  @Override
  public Allergen Read(int id) {
    return dbr.readAllergenByID(id);
  }

  @Override
  public int Update(Allergen allergen) {
    return 0;
  }

  @Override
  public int Delete(Allergen allergen) {
    return 0;
  }

  @Override
  public ArrayList<Allergen> ReadAll() {
    return dbr.readAllAllergens();
  }

  public Allergen readAllergenByUserID(int id){
    return dbr.readAllergenByChildID(id);
  }
}
