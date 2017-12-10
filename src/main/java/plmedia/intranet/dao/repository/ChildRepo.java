package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.model.Child;

/**
 * A repository for child objects.
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 * @author Andreas Nissen
 */

@Repository
public class ChildRepo implements IRepo<Child> {

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();
  DBupdate dbu = new DBupdate();

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


  public ArrayList<Integer> ReadChildrenIDbyParentID(int id) {
    System.out.println("hej fra children");
     return dbr.readChildrenIDByParentID(id);}

  @Override
  public int Update(Child child) {
    return dbu.updateChild(child);
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
