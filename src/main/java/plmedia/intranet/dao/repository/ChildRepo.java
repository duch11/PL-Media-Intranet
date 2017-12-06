package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.model.Child;

/**
 * A repository for child objects.
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */

@Repository
public class ChildRepo implements IRepo<Child> {

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();

  /**
   * Creates initial child object.
   * @param child
   * @return child
   */
  @Override
  public int Create(Child child) {
    return dbc.createChild(child);
  }

  @Override
  public Child Read(int id) {
    return dbr.readChildById(id);
  }

  @Override
  public int Update(Child child) {
    return 0;
  }

  @Override
  public int Delete(Child child) {
    return 0;
  }

  @Override
  public ArrayList<Child> ReadAll() {
    return dbr.readAllChildren();
  }
}
