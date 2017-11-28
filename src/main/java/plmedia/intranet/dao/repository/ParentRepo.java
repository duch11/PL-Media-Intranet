package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.dao.DBUtil;
import plmedia.intranet.model.Parent;


/**
 * @author Tobias Thomsen
 * @author Simon le Févre Ryom
 */

public class ParentRepo<T> implements IRepo<Parent>, IUserRepo<Parent> {

  DBUtil db = new DBUtil();

  @Override
  public int Create(Parent parent) {
    return db.createParent(parent);
  }

  @Override
  public Parent Read(int i) {
    return null;
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
    return null;
  }




}
