package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBdelete;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.dao.DBUtil.DBupdate;
import plmedia.intranet.model.Parent;


/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

@Repository
public class ParentRepo<T> implements IRepo<Parent> {

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();
  DBupdate dbu = new DBupdate();
  DBdelete dbd = new DBdelete();

  @Override
  public int Create(Parent parent) {
    return dbc.createParent(parent);
  }

  @Override
  public Parent Read(int id) {
    return dbr.readParentByID(id);
  }

  @Override
  public int Update(Parent parent) {
    return dbu.updateParent(parent);
  }

  @Override
  public int Delete(Parent parent) {
    return dbd.deleteParent(parent);
  }

  @Override
  public ArrayList<Parent> ReadAll() {
    return dbr.readAllParents();
  }

  public Parent readParentByEmail(String userEmail) {
    return dbr.readParentByEmail(userEmail);
  }

  public ArrayList<Parent> readParentByChildID(int id) {
    return dbr.readParentByChildID(id);
  }

}
