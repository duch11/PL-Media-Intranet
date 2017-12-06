package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import plmedia.intranet.dao.DBUtil.DBcreate;
import plmedia.intranet.dao.DBUtil.DBread;
import plmedia.intranet.model.Parent;


/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

@Repository
public class ParentRepo<T> implements IRepo<Parent>, IUserRepo<Parent> {

  DBcreate dbc = new DBcreate();
  DBread dbr = new DBread();

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
    return 0;
  }

  @Override
  public int Delete(Parent parent) {
    return 0;
  }

  @Override
  public ArrayList<Parent> ReadAll() {
    return dbr.readAllParents();
  }




}
