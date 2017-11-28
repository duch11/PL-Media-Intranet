package plmedia.intranet.dao.repository;

import java.util.ArrayList;
import plmedia.intranet.model.Child;

public class ChildRepo implements IRepo<Child> {

  @Override
  public int Create(Child child) {
    return 0;
  }

  @Override
  public Child Read(int i) {
    return null;
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
    return null;
  }
}
