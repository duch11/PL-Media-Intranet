package plmedia.intranet.dao.repository;

import java.util.ArrayList;

public interface IRepo<T> {

  public int Create(T t);

  public T Read(int id);

  public int Update(T t);

  public int Delete(T t);

  public ArrayList<T> ReadAll();
}
