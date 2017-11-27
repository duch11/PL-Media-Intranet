package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.model.Parent;

/**
 * @author Tobias Thomsen
 */

public class ParentRepo<T> implements IRepo<Parent>, IUserRepo<Parent> {

  @Override
  public int Create(Parent parent) {
    return 0;
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

  @Override
  public boolean CheckEmail(String email) {
    return false;
  }



}
