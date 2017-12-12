package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBdelete;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.model.Allergen;
import plmedia.intranet.model.Child;

/**
 * A repository for Allergens. Takes in logic from DBUtil package.
 * @author Simon le Févre Ryom
 * @author Andreas Nissen
 */
@Repository
public class AllergenRepo implements IRepo<Allergen> {

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();
  DBupdate dbu = new DBupdate();
  DBdelete dbd = new DBdelete();

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
    return dbu.updateAllergen(allergen);
  }

  @Override
  public int Delete(Allergen allergen) {
    return dbd.deleteAllergen(allergen);
  }

  @Override
  public ArrayList<Allergen> ReadAll() {
    return dbr.readAllAllergens();
  }

  public ArrayList<Allergen> readAllergenByChildID(int id){
    return dbr.readAllergenByChildID(id);
  }

  public int updateChildAllergens(Child child, ArrayList<Integer> newAllergen) {
    return dbu.updateChildAllergens(child, newAllergen);
  }

}

