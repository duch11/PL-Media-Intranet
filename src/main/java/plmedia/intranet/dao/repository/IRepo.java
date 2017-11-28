package plmedia.intranet.dao.repository;

import java.util.ArrayList;

/**
 *
 * @author Simon le Févre Ryom
 * @author Tobias Thomsen
 */
public interface IRepo<T> {

  /**
   *
   * @return
   */
  public int Create(T t);

  /**
   *
   * @param i
   * @return
   */
  public T Read(int i);

  /**
   * Updates the user
   * @return
   */
  public int Update(T t);

  /**
   * Deletes the user
   * @return
   */
  public int Delete(T t);


  /**
   * Reads all
   * @return
   */
  public ArrayList<T> ReadAll();
}
