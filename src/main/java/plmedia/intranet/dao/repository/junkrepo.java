package plmedia.intranet.dao.repository;

import java.util.ArrayList;

public class ChildRepo implements IRepo<Child> {

  public int Create(Child child){ ... }
    
  public T Read(int id){ ... }

  public int Update(Child child){ ... }

  public int Delete(Child child){ ... }

  public ArrayList<Child> ReadAll(){ ... }
}


